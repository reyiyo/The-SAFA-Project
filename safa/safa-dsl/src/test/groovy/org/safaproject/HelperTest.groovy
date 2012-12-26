//
// Generated from archetype; please customize.
//

package org.safaproject

import org.safaproject.Helper
import org.safaproject.Example

/**
 * Tests for the {@link Helper} class.
 */
class HelperTest
    extends GroovyTestCase
{
    void testHelp() {
        new Helper().help(new Example())
    }
}
