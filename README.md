# Android ini文件解析工具
Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
	    ...
		maven { url 'https://jitpack.io' }
	}
}
```
Add the dependency
```
implementation 'com.github.donghaowxr:IniManager:v1.0'
```
JAVA Sample Code
```
try {
	IniReader iniReader = IniReader.Companion.parse("/sdcard/config.ini");
	String value = iniReader.getValue("section", "name");
} catch (IOException e) {
	e.printStackTrace();
}
```
Kotlin Sample Code
```
val iniReader = IniReader.parse("sdcard/config.ini")
val value = iniReader.getValue("section", "name")
```