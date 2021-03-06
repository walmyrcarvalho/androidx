/*
 * Copyright 2020 The Android Open Source Project
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

package androidx.camera.lifecycle;

import static java.lang.annotation.RetentionPolicy.CLASS;

import androidx.annotation.experimental.Experimental;

import java.lang.annotation.Retention;

/**
 * Same as {@link androidx.camera.core.ExperimentalUseCaseGroup}. Should only be used in
 * lifecycle artifact.
 *
 * <p> The duplication is to workaround an issue where experimental annotation from a
 * different artifact cannot be properly applied. Adding this annotation hides the API from
 * current.txt.
 *
 * @see androidx.camera.core.ExperimentalUseCaseGroup
 *
 * TODO(b/159033688): Remove after the bug is fixed.
 */
@Retention(CLASS)
@Experimental
public @interface ExperimentalUseCaseGroupLifecycle {
}
