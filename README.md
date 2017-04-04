Ti.IEC60870
===========

This module is the Appcelerator Titanium implementation of [IEC 60870-5-104 communication standard](https://en.wikipedia.org/wiki/IEC_60870-5). It is an alternative solution of [NeoSCADA](https://wiki.eclipse.org/EclipseNeoSCADA/IEC60870).104
Thanks to [Stefan Feuerhahn](http://birea.infai.org/referenten/stefan-feuerhahn-fraunhofer-institut-fur-solare-energiesysteme-ise/) from Fraunhofer Institute for Solar Energy Systems for the [Java implementation](https://www.openmuc.org/iec-60870-5-104/user-guide/). 

The module is work on progress and not ready for production.

![](https://raw.githubusercontent.com/AppWerft/Ti.IEC60870/master/logo.png)
Usage as client
---------------

First we need a connection to the server:

```javascript
var IEC60870 = require("de.appwerft.j60870");

var conn = IEC60870.createConnection({
	address : "192.168.0.3",
	port : 1234,
	commonAddressFieldLength : INT, // length of the Common Address (CA) field of the ASDU
	cotFieldLength : INT, // length of the Cause Of Transmission (COT) field of the ASDU
	ioaFieldLength : INT, //  length of the Information Object Address (IOA) field of the ASDU
	maxIdleTime : INT, // maximum time in ms that the connection may be idle before sending a test frame
	maxTimeNoAckReceived : INT, // maximum time in ms that no acknowledgement has been received (for I-Frames or Test-Frames) before actively closing the connection. 
	maxTimeNoAckSent : INT,
	maxUnconfirmedIPdusReceived : INT
});
```

Alternatively to constructor parameters you can provide a json file in you folder Ressources. In this case you can simple connect by
```javascript
var conn = IEC60870.createConnection();
conn.connect(require("onConnectHandler"),onErrorHandler);
```
The default path of configuration file `j60870.json` you can overwrite  in your tiapp.xml with parameter `J60870_PATH`. In this case you can only connect one server.  

After creation of a connection you can connect:


Inside of `onConnectHandler.js` the work will done. The payload is an array of informationObjects. Every informationObject is an array of different informationElements. 
```javascript
module.exports = function(_connection) {

    /*
    A set of Information Elements or a sequence of information element sets. 
    The type of information elements in the set and their order depend 
    on the ASDU's TypeId and is the same for all information objects within one ASDU. 
    If the sequence bit is set in the ASDU then the ASDU contains a single Information Object 
    containing a sequence of information element sets. If the sequence bit is not set the ASDU 
    contains a sequence of information objects each containing only single information elements sets.
    */
    /* In Titanium implementation a JSONArray is using: */
    var elems = [[{"type":"AFQ","opts" : [3, 18]},{"type":"BCR","opts" : [31, 17, true,true, false]}]];
    // must be valide ;-)
    var infoObject = IEC60870.createInformationObject({
        address : 234,  // IOA
        elems : elems
    });
    var ASdu = IEC60870.createASdu({
        typeId : "C_BO_NA_1",
        causeOfTransmission : "ACTIVATION",
        isSequenceOfElements : true,
        test : false,
        negativeConfirm : false
        originatorAddress : 123, // the address of the originating controlling station so that responses can be routed back to it
        commonAddress : 23, //the address of the target station or the broadcast address.
        informationObjects : [infoObject]    
    });
    _connection.startDataTransfer(ASdu);
};

```
