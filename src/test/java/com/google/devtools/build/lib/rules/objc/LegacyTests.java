// Copyright 2017 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.devtools.build.lib.rules.objc;

import static com.google.common.base.Predicates.and;

import com.google.common.base.Predicate;
import com.google.devtools.build.lib.testutil.BazelTestSuiteBuilder;
import com.google.devtools.build.lib.testutil.CustomSuite;
import com.google.devtools.build.lib.testutil.TestSuiteBuilder;
import java.util.Set;
import org.junit.runner.RunWith;

/**
 * A suite to run all tests annotated with {@link LegacyTest}.
 */
@RunWith(CustomSuite.class)
public class LegacyTests extends BazelTestSuiteBuilder {
  /** A predicate that succeeds only if the test is annotated @{link LegacyTest}. */
  public static final Predicate<Class<?>> IS_LEGACY =
      testClass -> testClass.isAnnotationPresent(LegacyTest.class);

  @SuppressWarnings("unchecked") // varargs parameter.
  public static Set<Class<?>> suite() {
    return new TestSuiteBuilder()
        .addPackageRecursive("com.google.devtools.build.lib.rules.objc")
        .matchClasses(and(
            BazelTestSuiteBuilder.TEST_SUPPORTS_CURRENT_OS,
            IS_LEGACY))
        .create();
  }
}
