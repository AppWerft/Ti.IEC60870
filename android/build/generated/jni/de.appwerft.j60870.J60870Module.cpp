/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2011-2016 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */

/** This code is generated, do not edit by hand. **/

#include "de.appwerft.j60870.J60870Module.h"

#include "AndroidUtil.h"
#include "EventEmitter.h"
#include "JNIUtil.h"
#include "JSException.h"
#include "Proxy.h"
#include "ProxyFactory.h"
#include "TypeConverter.h"
#include "V8Util.h"


#include "de.appwerft.j60870.InformationElementModule.h"
#include "de.appwerft.j60870.ClientModule.h"
#include "de.appwerft.j60870.ServerModule.h"

#include "de.appwerft.j60870.ConnectionProxy.h"
#include "de.appwerft.j60870.ASduProxy.h"
#include "de.appwerft.j60870.InformationObjectProxy.h"

#include "org.appcelerator.kroll.KrollModule.h"

#define TAG "J60870Module"

using namespace v8;

namespace de {
namespace appwerft {
namespace j60870 {


Persistent<FunctionTemplate> J60870Module::proxyTemplate;
jclass J60870Module::javaClass = NULL;

J60870Module::J60870Module(jobject javaObject) : titanium::Proxy(javaObject)
{
}

void J60870Module::bindProxy(Local<Object> exports, Local<Context> context)
{
	Isolate* isolate = context->GetIsolate();

	Local<FunctionTemplate> pt = getProxyTemplate(isolate);
	Local<Function> proxyConstructor = pt->GetFunction(context).ToLocalChecked();
	Local<String> nameSymbol = NEW_SYMBOL(isolate, "J60870"); // use symbol over string for efficiency
	Local<Object> moduleInstance = proxyConstructor->NewInstance(context).ToLocalChecked();
	exports->Set(nameSymbol, moduleInstance);
}

void J60870Module::dispose(Isolate* isolate)
{
	LOGD(TAG, "dispose()");
	if (!proxyTemplate.IsEmpty()) {
		proxyTemplate.Reset();
	}

	titanium::KrollModule::dispose(isolate);
}

Local<FunctionTemplate> J60870Module::getProxyTemplate(Isolate* isolate)
{
	if (!proxyTemplate.IsEmpty()) {
		return proxyTemplate.Get(isolate);
	}

	LOGD(TAG, "GetProxyTemplate");

	javaClass = titanium::JNIUtil::findClass("de/appwerft/j60870/J60870Module");
	EscapableHandleScope scope(isolate);

	// use symbol over string for efficiency
	Local<String> nameSymbol = NEW_SYMBOL(isolate, "J60870");

	Local<FunctionTemplate> t = titanium::Proxy::inheritProxyTemplate(isolate,
		titanium::KrollModule::getProxyTemplate(isolate)
, javaClass, nameSymbol);

	proxyTemplate.Reset(isolate, t);
	t->Set(titanium::Proxy::inheritSymbol.Get(isolate),
		FunctionTemplate::New(isolate, titanium::Proxy::inherit<J60870Module>)->GetFunction());

	titanium::ProxyFactory::registerProxyPair(javaClass, *t);

	// Method bindings --------------------------------------------------------
	titanium::SetProtoMethod(isolate, t, "createElements", J60870Module::createElements);
	titanium::SetProtoMethod(isolate, t, "example", J60870Module::example);

	Local<ObjectTemplate> prototypeTemplate = t->PrototypeTemplate();
	Local<ObjectTemplate> instanceTemplate = t->InstanceTemplate();

	// Delegate indexed property get and set to the Java proxy.
	instanceTemplate->SetIndexedPropertyHandler(titanium::Proxy::getIndexedProperty,
		titanium::Proxy::setIndexedProperty);

	// Constants --------------------------------------------------------------

	// Dynamic properties -----------------------------------------------------
	instanceTemplate->SetAccessor(NEW_SYMBOL(isolate, "exampleProp"),
			J60870Module::getter_exampleProp,
			J60870Module::setter_exampleProp,
			Local<Value>(), DEFAULT,
			static_cast<v8::PropertyAttribute>(v8::DontDelete)
		);

	// Accessors --------------------------------------------------------------

	return scope.Escape(t);
}

// Methods --------------------------------------------------------------------
void J60870Module::createElements(const FunctionCallbackInfo<Value>& args)
{
	LOGD(TAG, "createElements()");
	Isolate* isolate = args.GetIsolate();
	HandleScope scope(isolate);

	JNIEnv *env = titanium::JNIScope::getEnv();
	if (!env) {
		titanium::JSException::GetJNIEnvironmentError(isolate);
		return;
	}
	static jmethodID methodID = NULL;
	if (!methodID) {
		methodID = env->GetMethodID(J60870Module::javaClass, "createElements", "(Lorg/appcelerator/kroll/KrollDict;)V");
		if (!methodID) {
			const char *error = "Couldn't find proxy method 'createElements' with signature '(Lorg/appcelerator/kroll/KrollDict;)V'";
			LOGE(TAG, error);
				titanium::JSException::Error(isolate, error);
				return;
		}
	}

	Local<Object> holder = args.Holder();
	// If holder isn't the JavaObject wrapper we expect, look up the prototype chain
	if (!JavaObject::isJavaObject(holder)) {
		holder = holder->FindInstanceInPrototypeChain(getProxyTemplate(isolate));
	}

	titanium::Proxy* proxy = titanium::Proxy::unwrap(holder);

	if (args.Length() < 1) {
		char errorStringBuffer[100];
		sprintf(errorStringBuffer, "createElements: Invalid number of arguments. Expected 1 but got %d", args.Length());
		titanium::JSException::Error(isolate, errorStringBuffer);
		return;
	}

	jvalue jArguments[1];




	bool isNew_0;

	if (!args[0]->IsNull()) {
		Local<Value> arg_0 = args[0];
		jArguments[0].l =
			titanium::TypeConverter::jsObjectToJavaKrollDict(
				isolate,
				env, arg_0, &isNew_0);
	} else {
		jArguments[0].l = NULL;
	}

	jobject javaProxy = proxy->getJavaObject();
	env->CallVoidMethodA(javaProxy, methodID, jArguments);

	if (!JavaObject::useGlobalRefs) {
		env->DeleteLocalRef(javaProxy);
	}



			if (isNew_0) {
				env->DeleteLocalRef(jArguments[0].l);
			}


	if (env->ExceptionCheck()) {
		titanium::JSException::fromJavaException(isolate);
		env->ExceptionClear();
	}




	args.GetReturnValue().Set(v8::Undefined(isolate));

}
void J60870Module::example(const FunctionCallbackInfo<Value>& args)
{
	LOGD(TAG, "example()");
	Isolate* isolate = args.GetIsolate();
	HandleScope scope(isolate);

	JNIEnv *env = titanium::JNIScope::getEnv();
	if (!env) {
		titanium::JSException::GetJNIEnvironmentError(isolate);
		return;
	}
	static jmethodID methodID = NULL;
	if (!methodID) {
		methodID = env->GetMethodID(J60870Module::javaClass, "example", "()Ljava/lang/String;");
		if (!methodID) {
			const char *error = "Couldn't find proxy method 'example' with signature '()Ljava/lang/String;'";
			LOGE(TAG, error);
				titanium::JSException::Error(isolate, error);
				return;
		}
	}

	Local<Object> holder = args.Holder();
	// If holder isn't the JavaObject wrapper we expect, look up the prototype chain
	if (!JavaObject::isJavaObject(holder)) {
		holder = holder->FindInstanceInPrototypeChain(getProxyTemplate(isolate));
	}

	titanium::Proxy* proxy = titanium::Proxy::unwrap(holder);

	jvalue* jArguments = 0;

	jobject javaProxy = proxy->getJavaObject();
	jstring jResult = (jstring)env->CallObjectMethodA(javaProxy, methodID, jArguments);



	if (!JavaObject::useGlobalRefs) {
		env->DeleteLocalRef(javaProxy);
	}



	if (env->ExceptionCheck()) {
		Local<Value> jsException = titanium::JSException::fromJavaException(isolate);
		env->ExceptionClear();
		return;
	}

	if (jResult == NULL) {
		args.GetReturnValue().Set(Null(isolate));
		return;
	}

	Local<Value> v8Result = titanium::TypeConverter::javaStringToJsString(isolate, env, jResult);

	env->DeleteLocalRef(jResult);


	args.GetReturnValue().Set(v8Result);

}

// Dynamic property accessors -------------------------------------------------

void J60870Module::getter_exampleProp(Local<Name> property, const PropertyCallbackInfo<Value>& args)
{
	Isolate* isolate = args.GetIsolate();
	HandleScope scope(isolate);

	JNIEnv *env = titanium::JNIScope::getEnv();
	if (!env) {
		titanium::JSException::GetJNIEnvironmentError(isolate);
		return;
	}
	static jmethodID methodID = NULL;
	if (!methodID) {
		methodID = env->GetMethodID(J60870Module::javaClass, "getExampleProp", "()Ljava/lang/String;");
		if (!methodID) {
			const char *error = "Couldn't find proxy method 'getExampleProp' with signature '()Ljava/lang/String;'";
			LOGE(TAG, error);
				titanium::JSException::Error(isolate, error);
				return;
		}
	}

	titanium::Proxy* proxy = titanium::Proxy::unwrap(args.Holder());

	if (!proxy) {
		args.GetReturnValue().Set(Undefined(isolate));
		return;
	}

	jvalue* jArguments = 0;

	jobject javaProxy = proxy->getJavaObject();
	jstring jResult = (jstring)env->CallObjectMethodA(javaProxy, methodID, jArguments);



	if (!JavaObject::useGlobalRefs) {
		env->DeleteLocalRef(javaProxy);
	}



	if (env->ExceptionCheck()) {
		Local<Value> jsException = titanium::JSException::fromJavaException(isolate);
		env->ExceptionClear();
		return;
	}

	if (jResult == NULL) {
		args.GetReturnValue().Set(Null(isolate));
		return;
	}

	Local<Value> v8Result = titanium::TypeConverter::javaStringToJsString(isolate, env, jResult);

	env->DeleteLocalRef(jResult);


	args.GetReturnValue().Set(v8Result);

}

void J60870Module::setter_exampleProp(Local<Name> property, Local<Value> value, const PropertyCallbackInfo<void>& args)
{
	Isolate* isolate = args.GetIsolate();
	HandleScope scope(isolate);

	JNIEnv *env = titanium::JNIScope::getEnv();
	if (!env) {
		LOGE(TAG, "Failed to get environment, exampleProp wasn't set");
		return;
	}

	static jmethodID methodID = NULL;
	if (!methodID) {
		methodID = env->GetMethodID(J60870Module::javaClass, "setExampleProp", "(Ljava/lang/String;)V");
		if (!methodID) {
			const char *error = "Couldn't find proxy method 'setExampleProp' with signature '(Ljava/lang/String;)V'";
			LOGE(TAG, error);
		}
	}

	titanium::Proxy* proxy = titanium::Proxy::unwrap(args.Holder());
	if (!proxy) {
		return;
	}

	jvalue jArguments[1];

	

	if (!value->IsNull()) {
		Local<Value> arg_0 = value;
		jArguments[0].l =
			titanium::TypeConverter::jsValueToJavaString(
				isolate,
				env, arg_0);
	} else {
		jArguments[0].l = NULL;
	}

	jobject javaProxy = proxy->getJavaObject();
	env->CallVoidMethodA(javaProxy, methodID, jArguments);

	if (!JavaObject::useGlobalRefs) {
		env->DeleteLocalRef(javaProxy);
	}



				env->DeleteLocalRef(jArguments[0].l);


	if (env->ExceptionCheck()) {
		titanium::JSException::fromJavaException(isolate);
		env->ExceptionClear();
	}




}



} // j60870
} // appwerft
} // de
