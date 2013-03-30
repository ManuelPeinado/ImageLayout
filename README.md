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

Add an element named <code>com.manuelpeinado.imagelayout.ImageLayout</code> to  your XML layout. This element should have the following attributes:

* <code>custom:image</code>. The drawable to be used as the background for the view.

* <code>custom:imageWidth / imageHeight</code>. The dimensions of the image in which the layout coordinates of the children are expressed.

In addition, you can use the following optional attributes:

* <code>custom:fit</code>. Determines how the background image is drawn. Accepted values are:
	*  <code>vertical</code>. The image is made to fill the available vertical space, and may be cropped horizontally if there is not enough space. If there is too much space it is left blank. The horizontal position of the image is controlled by the <code>android:gravity</code> attribute.
	*  <code>horizontal</code>. The image is made to fill the available horizontal space, and may be cropped vertically if there is not enough space. If there is too much space it is left blank. The vertical position of the image is controlled by the <code>android:gravity</code> attribute.
	*  <code>auto</code>. This is the default value. The image is made to fill the available space vertically in portrait mode and horizontally in landscape. Note that the library does not determine the orientation based on the actual device orientation, but on the relative aspect ratios of the image and the view.
	
The following image shows the result of applying these values in portrait orientation:

![Fit portrait image][3]

And in landscape:

![Fit portrait image][4]

Note that this is not a good candidate for the *auto* mode, as in both orientations significant image parts are cropped. See the "New York City" activity in the demo app for an example in which using this mode makes more sense.


Coming soon
-----------

* An "adjustViewBounds" attribute similar to the one in <code>ImageView</code>.

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
[3]: https://raw.github.com/ManuelPeinado/ImageLayout/master/art/fit_portrait-small.png
[4]: https://raw.github.com/ManuelPeinado/ImageLayout/master/art/fit_portrait-small.png
