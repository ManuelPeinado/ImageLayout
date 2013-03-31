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

Usage
-----

### Adding to your layout


Add an element named <code>com.manuelpeinado.imagelayout.ImageLayout</code> to  your XML layout. This element should have the following attributes:

##### <code>custom:image</code>
The drawable to be used as the background for the view.

##### <code>custom:imageWidth/Height</code>
The dimensions of the image in which the layout coordinates of the children are expressed.

In addition, you can use the following optional attributes:

##### <code>custom:fit</code>

Determines how the background image is drawn. Accepted values are:

*  <code>vertical</code>. The image is made to fill the available vertical space, and may be cropped horizontally if there is not enough space. If there is too much horizontal space, it is left blank. The horizontal position of the image is controlled by the <code>android:gravity</code> attribute.

*  <code>horizontal</code>. The image is made to fill the available horizontal space, and may be cropped vertically if there is not enough space. If there is too much vertical space, it is left blank. The vertical position of the image is controlled by the <code>android:gravity</code> attribute.

*  <code>both</code>. The image fills the available space both vertically and horizontally. If the aspect ratio of the image does not match exactly the aspect ratio of the available space, the image is cropped either vertically or horizontally, depending of which provides the best fit.

*  <code>auto</code>. This is the default value. The image is made to fill the available space vertically in portrait mode and horizontally in landscape. Note that the library does not determine the orientation based on the actual device orientation, but on the relative aspect ratios of the image and the view.
	
Check the "Fit attribute" sample in the demo application to see these differente modes in action.

### Adding children

You can add child views inside your <ImageLayout> element just like you would to any other ViewGroup. But instead of using the android:layout_width and android:layout_height attributes to control how a child is positioned within ImageLayout, you should use the following:

##### <code>custom:layout_width/height</code>

Similar to android:layout_width/height, but expressed in image coordinates. 

##### <code>custom:layout_maxWidth/maxHeight</code>

Similar to android:layout_width/height, but expressed in image coordinates.

##### <code>custom:layout_centerX/Y</code>

Center of the child view, in image coordinates.

##### <code>custom:layout_left/top/right/bottom</code>

Bounds of the child view, in image coordinates.

Note that depending of your application you will use a different combination of these attributes. For example might specify a value for custom:right in order to align the right side of your view with a given feature of the image, and custom:centerY to align the same view vertically with another feature.

Also note that the size-related attributes such as custom:layout_width are not mandatory. If you don't specify any of them for a given dimension, the view is measured in the traditional "wrap content" fashion.


View size adjustment
--------------------


Developed By
--------------------

Manuel Peinado Gallego - <manuel.peinado@gmail.com>

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
