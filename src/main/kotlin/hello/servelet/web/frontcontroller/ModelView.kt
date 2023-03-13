package hello.servelet.web.frontcontroller

import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class ModelView(
    var viewName: String,
) {

    var model: MutableMap<String, Any> = HashMap()
}