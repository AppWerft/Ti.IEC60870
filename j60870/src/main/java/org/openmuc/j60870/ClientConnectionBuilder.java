/*
 * Copyright 2014-17 Fraunhofer ISE
 *
 * This file is part of j60870.
 * For more information visit http://www.openmuc.org
 *
 * j60870 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * j60870 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with j60870.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.openmuc.j60870;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

/**
 * The client connection builder is used to connect to IEC 60870-5-104 servers. A client application that wants to
 * connect to a server should first create an instance of {@link ClientConnectionBuilder}. Next all the necessary
 * configuration parameters can be set. Finally the {@link ClientConnectionBuilder#connect() connect} function is called
 * to connect to the server. An instance of {@link ClientConnectionBuilder} can be used to create an unlimited number of
 * connections. Changing the parameters of a {@link ClientConnectionBuilder} has no affect on connections that have
 * already been created.
 *
 * Note that the configured lengths of the fields COT, CA and IOA have to be the same for all communicating nodes in a
 * network. The default values used by {@link ClientConnectionBuilder} are those most commonly used in IEC 60870-5-104
 * communication.
 *
 * @author Stefan Feuerhahn
 *
 */
public class ClientConnectionBuilder extends CommonBuilder<ClientConnectionBuilder> {

    private SocketFactory socketFactory = SocketFactory.getDefault();
    private InetAddress address;
    private int port = 2404;
    InetAddress localAddr = null;
    int localPort;

    /**
     * Creates a client connection builder that can be used to connect to the given address.
     * 
     * @param address
     *            the address to connect to
     */
    public ClientConnectionBuilder(InetAddress address) {
        this.address = address;
    }

    /**
     * Set the socket factory to used to create the socket for the connection. The default is
     * {@link SocketFactory#getDefault()}. You could pass an {@link SSLSocketFactory} to enable SSL.
     * 
     * @param socketFactory
     *            the socket factory
     * @return this builder
     */
    public ClientConnectionBuilder setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
        return this;
    }

    /**
     * Sets the port to connect to. The default port is 2404.
     * 
     * @param port
     *            the port to connect to.
     * @return this builder
     */
    public ClientConnectionBuilder setPort(int port) {
        this.port = port;
        return this;
    }

    /**
     * Sets the address to connect to.
     * 
     * @param address
     *            the address to connect to.
     * @return this builder
     */
    public ClientConnectionBuilder setAddress(InetAddress address) {
        this.address = address;
        return this;
    }

    /**
     * Sets the local (client) address and port the socket will connect to.
     * 
     * @param address
     *            the local address the socket is bound to, or null for any local address.
     * @param port
     *            the local port the socket is bound to or zero for a system selected free port.
     * @return this builder
     */
    public ClientConnectionBuilder setLocalAddress(InetAddress address, int port) {
        this.localAddr = address;
        this.localPort = port;
        return this;
    }

    /**
     * Connects to the server. The TCP/IP connection is build up and a {@link Connection} object is returned that can be
     * used to communicate with the server.
     *
     * @return the {@link Connection} object that can be used to communicate with the server.
     * @throws IOException
     *             if any kind of error occurs during connection build up.
     */
    public Connection connect() throws IOException {
        Socket socket;
        if (localAddr == null) {
            socket = socketFactory.createSocket(address, port);
        }
        else {
            socket = socketFactory.createSocket(address, port, localAddr, localPort);
        }

        return new Connection(socket, null, settings.getCopy());
    }

}
