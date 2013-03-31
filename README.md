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


Add an element named `com.manuelpeinado.imagelayout.ImageLayout` to  your XML layout. This element should have the following attributes:

| Attribute              | Description                        |
|------------------------|------------------------------------|
| `custom:image` | The drawable to be used as the background for the view.|
| `custom:imageWidth`<br>`custom:imageHeight` | The dimensions of the image in which the layout coordinates of the children are expressed. |

In addition, you can use the following optional attributes:

| Attribute              | Description                        |
|------------------------|------------------------------------|
| `custom:fit` | Determines how the background image is drawn. Accepted values are `vertical`, `horizontal`, `both` and `auto` (the default). Check the "Fit attribute" sample in the demo application to see these different modes in action.|
	

### Adding children

You can add child views to your ImageLayout just like you would to any other ViewGroup. But instead of using the `android:layout_width` and `android:layout_height` attributes to control the layout of children, you should use the following:

|Attribute                 |Description  |
|--------------------------|-------------|
| `custom:layout_width`<br>`custom:layout_height` | Similar to android:layout_width/height, but expressed in image coordinates |
|`custom:layout_maxWidth`<br>`custom:layout_maxHeight`| Similar to android:layout_maxWidth/maxHeight, but expressed in image coordinates. |
| `custom:layout_centerX`<br>`custom:layout_centerY`|Center of the child view, in image coordinates.|
|`custom:layout_left`<br>`custom:layout_top`<br>`custom:layout_right`<br>`custom:layout_bottom`|Bounds of the child view, in image coordinates.|

Note that depending of your application you will use a different combination of these attributes. You might for example specify a value for `custom:right` in order to align the right side of your view with a given feature of the image, and `custom:centerY` to align the same view vertically with another feature.

Also note that the size-related attributes such as `custom:layout_width` are not mandatory. If you don't specify any of them for a given dimension, the view is measured in the traditional "wrap content" fashion.


### View size adjustment

You can have your ImageLayout fill its parent by specifying `match_parent` in both its `android:layout_width` and `android:layout_height` attributes. But, since the aspect ratio of the image will typically differ from the aspect of ratio of the parent view, this will result in a  waste of screen real state in the form of blank margins around the image.

To prevent this, simply use `wrap_content` in either `android:layout_width` or `android:layout_height`. This will cause the ImageLayout to adopt a size which matches the aspect ratio of its image.

Please note that the result of using `wrap_content` for *both* `android:layout_width` and `android:layout_height` is unspecified.

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
