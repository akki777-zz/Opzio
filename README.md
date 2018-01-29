# Opzio
Java's Optional functionality with few modifications.

## Advantages
* No `get()` method, so no chance of NullPointerException.
* Added `ifAbsent()` method, which can be used in conjunction wuth `ifPresent()` for Error Handling.

## How-to-use
### Basic usage
* `orElse()` method is kinda necessary to get non-null value.
```
String name = (String) Opzio.ofNullable(null).orElse("default");
System.out.println(name); // prints "default"
```
* Otherwise do,
```
Opzio<String> name = Opzio.ofNullable(null);
System.out.println(name); // prints "Opzio.empty"
```

### Chaining
For hierarchy : `apiResponse -> result -> data -> firstName`,
* Do simply (using `resolve` method),
```
apiResponse.result.data.firstName = "Name";
Opzio.resolve(() -> apiResponse.result.data.firstName)
      .ifPresent(firstName -> System.out.println(firstName)) //prints "Name"
      .ifAbsent(() -> System.out.println("Error handling"));
```
* Or use `map` method,
```
apiResponse.result.data = null;
Opzio.ofNullable(apiResponse).map(v -> v.result).map(v -> v.data).map(v -> v.firstName)
     .ifPresent(firstName -> System.out.println(firstName))
     .ifAbsent(() -> System.out.println("Error handling")); // prints "Error handling"
```
## Where-to-use
* Android projects, with minSdkversion < 24 and/or android-gradle-plugin version < 3.
  * Such projects can't use Java 8 features. But if using Retrolambda, can make use of <b>Opzio</b>.

## Acknowledgements
* Benjamin [http://winterbe.com/posts/2015/03/15/avoid-null-checks-in-java/]
* Beildung [http://www.baeldung.com/java-optional]
* Oracle Java Optional class

## Licence
Licensed under the MIT license.

Copyright (c) 2015 [@akki777](http://github.com/akki777).
