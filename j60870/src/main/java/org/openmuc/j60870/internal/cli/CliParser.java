package org.openmuc.j60870.internal.cli;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CliParser {

    private final String name;
    private final String description;
    private String selectedGroup = "";

    private static final String HELP = "--help";

    private static class ParameterGroup {
        private final String name;
        private final List<CliParameter> parameters;

        public ParameterGroup(String name, List<CliParameter> parameters) {
            this.name = name;
            this.parameters = parameters;
        }
    }

    private final List<ParameterGroup> commandLineParameterGroups = new ArrayList<>();

    public CliParser(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addParameterGroup(String groupName, List<CliParameter> parameters) {
        commandLineParameterGroups.add(new ParameterGroup(groupName.toLowerCase(), parameters));
    }

    public void addParameters(List<CliParameter> parameters) {
        commandLineParameterGroups.clear();
        commandLineParameterGroups.add(new ParameterGroup("", parameters));
    }

    public String getSelectedGroup() {
        return selectedGroup;
    }

    public void parseArguments(String[] args) throws CliParseException {

        List<CliParameter> parameters = null;

        int i = 0;
        if (commandLineParameterGroups.get(0).name.isEmpty()) {
            parameters = commandLineParameterGroups.get(0).parameters;
        }
        else {
            if (args.length == 0) {
                throw new CliParseException("No parameters found.");
            }
            else if (HELP.equals(args[0])) {
                System.out.println(getUsageString());
                System.exit(0);
            }
            for (ParameterGroup parameterGroup : commandLineParameterGroups) {
                if (parameterGroup.name.equals(args[0].toLowerCase())) {
                    selectedGroup = parameterGroup.name;
                    parameters = parameterGroup.parameters;
                }
            }
            if (parameters == null) {
                throw new CliParseException("Group name " + args[0] + " is undefined.");
            }
            i++;
        }

        while (i < args.length) {
            boolean found = false;
            for (CliParameter option : parameters) {
                if (args[i].equals(option.getName())) {
                    i += option.parse(args, i);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new CliParseException("Unknown parameter found: " + args[i]);
            }
        }

        for (CliParameter option : parameters) {
            if (!option.isOptional() && !option.isSelected()) {
                throw new CliParseException("Parameter " + option.getName() + " is mandatory but was not selected.");
            }
        }
    }

    public String getUsageString() {

        StringBuilder sb = new StringBuilder();
        sb.append("NAME\n\t").append(name).append(" - ").append(description).append("\n\nSYNOPSIS\n");

        for (ParameterGroup parameterGroup : commandLineParameterGroups) {
            sb.append("\t").append(name).append(" ").append(parameterGroup.name);

            int characterColumn = name.length() + parameterGroup.name.length() + 1;

            for (CliParameter parameter : parameterGroup.parameters) {
                if ((characterColumn + parameter.appendSynopsis(new StringBuilder())) > 90) {
                    characterColumn = 0;
                    sb.append("\n\t    ");
                }
                sb.append(' ');
                characterColumn += parameter.appendSynopsis(sb) + 1;
            }
            sb.append("\n");
        }

        sb.append("\nOPTIONS\n");

        Set<CliParameter> parameters = new LinkedHashSet<>();

        for (ParameterGroup parameterGroup : commandLineParameterGroups) {
            parameters.addAll(parameterGroup.parameters);
        }

        for (CliParameter parameter : parameters) {
            sb.append(' ');
            parameter.appendDescription(sb);
            sb.append("\n\n");
        }

        sb.append("\t--help display this help and exit");

        return sb.toString();
    }

}
