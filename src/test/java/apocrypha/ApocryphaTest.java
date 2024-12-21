/*
 * Copyright (C) 2023 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package apocrypha;

import org.junit.jupiter.api.Test;

public class ApocryphaTest {

    @Test
    void name() {
        Apocrypha apocrypha = Apocrypha.with.name("TEST");
        assert apocrypha.name.equals("TEST");
    }
}
