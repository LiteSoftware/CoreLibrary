# CoreLibrary

***CoreLibrary*** is a basic set of classes and extension functions.

[![](https://jitpack.io/v/LiteSoftware/CoreLibrary.svg)](https://jitpack.io/#LiteSoftware/CoreLibrary)

## Structure

***core***

    entity - Contains base Essences
  
    exception -  Contains basic Exceptions
 
    extension - Contains basic extensions
  
    mapper - Contains basic Mappers



## Usage

Add this in your root `build.gradle` at the end of `repositories` in `allprojects` section:
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

Then add this dependency to your **module-level** `build.gradle` in `dependencies` section:
```groovy
implementation 'com.github.LiteSoftware:CoreLibrary:$version'
```


## License

```
   Copyright 2021 Vitaliy Sychov. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
