package org.randomstack.jme;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.simsilica.lemur.GuiGlobals;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.util.logging.Level;
import java.util.logging.LogManager;

/**
 *  An abstract class that should be used as a starting point for a JME application. This implementation handles some
 *  default boilerplate code as:
 *  - configuring logging
 *  - loading application settings using {@link ApplicationSettingsFactory}
 *  - removing default key mappings
 *  - initializing Lemur {@link GuiGlobals}
 *  - initializing {@link ApplicationGlobals}
 *  Application settings can be
 *
 * @author remy
 */
public abstract class JmeLauncher extends SimpleApplication {

    public JmeLauncher() {
        this((AppState) null);
    }

    public JmeLauncher(AppState... initialStates) {
        super(initialStates);

        LogManager.getLogManager().getLogger("").setLevel(Level.ALL);
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

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
