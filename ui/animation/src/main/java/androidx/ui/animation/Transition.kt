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

package androidx.ui.animation

import androidx.animation.PropKey
import androidx.animation.TransitionAnimation
import androidx.animation.TransitionDefinition
import androidx.animation.TransitionState
import com.google.r4a.Children
import com.google.r4a.Composable
import com.google.r4a.Model
import com.google.r4a.composer
import com.google.r4a.memo
import com.google.r4a.unaryPlus

/**
 * Composable to use with TransitionDefinition-based animations.
 *
 * Example:
 *
 *     val definition = transitionDefinition { ... }
 *     val alpha = FloatPropKey()
 *
 *     @Composable
 *     fun TransitionBackground() {
 *         <Transition definition toState=State.Pressed> state ->
 *             <Background alpha=state[alpha]/>
 *         <Transition/>
 *     }
 */
@Composable
fun <T> Transition(
    definition: TransitionDefinition<T>,
    toState: T,
    @Children children: @Composable() (state: TransitionState) -> Unit
) {
    if (transitionsEnabled) {
        // TODO: Remove the second parameter that works around b/130349879 once this code is updated to the DSL syntax
        val model = +memo(definition, null) { TransitionModel(definition) }
        model.anim.toState(toState)
        <children state=model />
    } else {
        val state = +memo(definition, toState) { definition.getStateFor(toState) }
        <children state/>
    }
}

/**
 * Stores the enabled state for [Transition] animations. Useful for tests to disable
 * animations and have reliable screenshot tests.
 */
var transitionsEnabled = true

// TODO(Doris): Use Clock idea instead of TransitionModel with pulse
@Model
private class TransitionModel<T>(
    transitionDef: TransitionDefinition<T>
) : TransitionState {

    private var animationPulse = 0L
    internal val anim: TransitionAnimation<T> =
        transitionDef.createAnimation().apply {
            onUpdate = {
                animationPulse++
            }
        }

    override fun <T> get(propKey: PropKey<T>): T {
        // we need to access the animationPulse so Compose will record this @Model values usage.
        @Suppress("UNUSED_VARIABLE")
        val pulse = animationPulse
        return anim[propKey]
    }
}