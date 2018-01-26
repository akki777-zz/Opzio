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
```
ApiResponse apiResponse = new ApiResponse();
apiResponse.result.data = null;
Opzio.ofNullable(apiResponse3).map(v -> v.result).map(v -> v.data).map(v -> v.firstName)
     .ifPresent(firstName -> System.out.println(firstName))
     .ifAbsent(() -> System.out.println("Error handling"));
```

## Licence
Licensed under the MIT license.

Copyright (c) 2015 [@akki777](http://github.com/akki777).
