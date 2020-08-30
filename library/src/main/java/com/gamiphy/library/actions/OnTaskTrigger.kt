package com.gamiphy.library.actions


/**
 * A listener for Gamiphy bot actions/tasks.
 */
interface OnTaskTrigger {
    /**
     * Triggered whenever an action/task fired in Gamiphy bot.
     * Called when click get on custom tasks
     *
     * @param actionName: the name of the action fired inside Gamiphy bot.
     */
    fun onTaskTrigger(actionName: String)
}
