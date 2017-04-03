Ti.IEC60870-5-104
=================

This module is the Titanium implementation of IEC 60870-5-104 communication standard. The library can be used to program clients as well as servers. The module is work on progress and not ready for production.


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
conn.connect(require("onConnectHandler"),onErrorHandler);
```

Alternatively to constructor parameters you can provide a json file in you folder Ressources. In this case you can simple connect by
```javascript
var conn = IEC60870.createConnection();
conn.connect(require("onConnectHandler"),onErrorHandler);
```
The default path of configuration file `j60870.json` you can overwrite  in your tiapp.xml with parameter `J60870_PATH`. In this case you can only connect one server.  


Inside of `onConnectHandler.js` can can implement:
```javascript
module.exports = function(_connection) {
    _connection.startDataTransfer();
    _connection.send();
};

```
