/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.ui.res

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.ui.core.ContextAmbient

/**
 * Load a string resource.
 *
 * @param id the resource identifier
 * @return the string data associated with the resource
 */
@Composable
fun stringResource(@StringRes id: Int): String {
    val context = ContextAmbient.current
    return context.resources.getString(id)
}

/**
 * Load a string resource with formatting.
 *
 * @param id the resource identifier
 * @param formatArgs the format arguments
 * @return the string data associated with the resource
 */
@Composable
fun stringResource(@StringRes id: Int, vararg formatArgs: Any): String {
    val context = ContextAmbient.current
    return context.resources.getString(id, *formatArgs)
}

/**
 * Load a string resource.
 *
 * @param id the resource identifier
 * @return the string data associated with the resource
 */
@Composable
fun stringArrayResource(@ArrayRes id: Int): Array<String> {
    val context = ContextAmbient.current
    return context.resources.getStringArray(id)
}