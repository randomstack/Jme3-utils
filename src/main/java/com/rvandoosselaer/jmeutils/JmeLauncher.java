package com.rvandoosselaer.jmeutils;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.rvandoosselaer.jmeutils.util.LogUtils;
import com.simsilica.lemur.GuiGlobals;

/**
 *  An abstract class that can be used as a starting point for a JME application. This implementation handles some
 *  default boilerplate code as:
 *  - configure logging
 *  - loading and setting application settings using {@link ApplicationSettingsFactory}
 *  - removing default key mappings
 *  - initializing Lemur {@link GuiGlobals}
 *  - initializing {@link ApplicationGlobals}
 *
 * @author rvandoosselaer
 */
public abstract class JmeLauncher extends SimpleApplication {

    public JmeLauncher() {
        this((AppState) null);
    }

    public JmeLauncher(AppState... initialStates) {
        super(initialStates);

        LogUtils.forwardJULToSlf4j();

        setSettings(ApplicationSettingsFactory.getAppSettings());
        setPauseOnLostFocus(false);
        setShowSettings(false);
    }

    @Override
    public void simpleInitApp() {
        // remove the default escape mapping
        getInputManager().deleteMapping(SimpleApplication.INPUT_MAPPING_EXIT);
        // initialize lemur globals, so that the default components can find what they need.
        GuiGlobals.initialize(this);
        // initialize application globals
        ApplicationGlobals.initialize(this);
        // call the init method
        init();
    }

    public abstract void init();

}
