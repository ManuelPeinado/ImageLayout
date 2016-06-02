ImageLayout
===========

A layout that arranges its children in relation to a background image. The layout of each  child is specified in image coordinates (pixels), and the conversion to screen coordinates is performed automatically.   

The background image is adjusted so that it fills the available space.  

For some applications this might be a useful replacement for the now deprecated AbsoluteLayout.

![Example Image][1]

Try out the sample application:

<a href="https://play.google.com/store/apps/details?id=com.manuelpeinado.imagelayout.demo">
  <img alt="Android app on Google Play"
       src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

Or browse the [source code of the sample application][2] for a complete example of use.

Including in your project
-------------------------

If you’re using Eclipse with the ADT plugin you can include ImageLayout as a library project. Just create a new Android project in Eclipse using the library/ folder as the existing source, and add a reference to it to your application project.

If you use gradle to build your Android project you can simply add a dependency for this library:

```

dependencies {  
    mavenCentral()
    compile 'com.github.manuelpeinado.imagelayout:imagelayout:1.1.0'
}

```

Usage
-----

### Adding to your layout


Add an element named <tt>com.manuelpeinado.imagelayout.ImageLayout</tt> to  your XML layout. This element should have the following attributes:

| Attribute              | Description                        |
|------------------------|------------------------------------|
| <tt>custom:image</tt> | The drawable to be used as the background for the view.|
| <tt>custom:imageWidth</tt><br><tt>custom:imageHeight</tt> | The dimensions of the image in which the layout coordinates of the children are expressed. |

In addition, you can use the following optional attributes:

| Attribute              | Description                        |
|------------------------|------------------------------------|
| <tt>custom:fit</tt> | Determines how the background image is drawn. Accepted values are <tt>vertical</tt>, <tt>horizontal</tt>, <tt>both</tt> and <tt>auto</tt> (the default). Check the "Fit attribute" sample in the demo application to see these different modes in action.|
	

### Adding children

You can add child views to your ImageLayout just like you would to any other ViewGroup. But instead of using the <tt>android:layout_width</tt> and <tt>android:layout_height</tt> attributes to control the layout of children, you should use the following:

|Attribute                 |Description  |
|--------------------------|-------------|
| <tt>custom:layout_width</tt><br><tt>custom:layout_height</tt> | Similar to android:layout_width/height, but expressed in image coordinates |
|<tt>custom:layout_maxWidth</tt><br><tt>custom:layout_maxHeight</tt>| Similar to android:layout_maxWidth/maxHeight, but expressed in image coordinates. |
| <tt>custom:layout_centerX</tt><br><tt>custom:layout_centerY</tt>|Center of the child view, in image coordinates.|
|<tt>custom:layout_left</tt><br><tt>custom:layout_top</tt><br><tt>custom:layout_right</tt><br><tt>custom:layout_bottom</tt>|Bounds of the child view, in image coordinates.|

Note that depending of your application you will use a different combination of these attributes. You might for example specify a value for <tt>custom:right</tt> in order to align the right side of your view with a given feature of the image, and <tt>custom:centerY</tt> to align the same view vertically with another feature.

Also note that the size-related attributes such as <tt>custom:layout_width</tt> are not mandatory. If you don't specify any of them for a given dimension, the view is measured in the traditional "wrap content" fashion.


### View size adjustment

You can have your ImageLayout fill its parent by specifying <tt>match_parent</tt> in both its <tt>android:layout_width</tt> and <tt>android:layout_height</tt> attributes. But, since the aspect ratio of the image will typically differ from the aspect of ratio of the parent view, this will result in a  waste of screen real state in the form of blank margins around the image.

To prevent this, simply use <tt>wrap_content</tt> in either <tt>android:layout_width</tt> or <tt>android:layout_height</tt>. This will cause the ImageLayout to adopt a size which matches the aspect ratio of its image.

Please note that the result of using <tt>wrap_content</tt> for *both* <tt>android:layout_width</tt> and <tt>android:layout_height</tt> is unspecified.

Who's using it
--------------
 
*Does your app use ImageLayout? If you want to be featured on this list drop me a line.*

Developed By
--------------------

Manuel Peinado Gallego - <manuel.peinado@gmail.com>

<a href="https://twitter.com/mpg2">
  <img alt="Follow me on Twitter"
       src="https://raw.github.com/ManuelPeinado/NumericPageIndicator/master/art/twitter.png" />
</a>
<a href="https://plus.google.com/106514622630861903655">
  <img alt="Follow me on Twitter"
       src="https://raw.github.com/ManuelPeinado/NumericPageIndicator/master/art/google-plus.png" />
</a>
<a href="http://www.linkedin.com/pub/manuel-peinado-gallego/1b/435/685">
  <img alt="Follow me on Twitter"
       src="https://raw.github.com/ManuelPeinado/NumericPageIndicator/master/art/linkedin.png" />

License
-------

    Copyright 2013 Manuel Peinado

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
[1]: https://raw.github.com/ManuelPeinado/ImageLayout/master/art/readme_pic.png
[2]: https://github.com/ManuelPeinado/ImageLayout/tree/master/sample
