package com.gamiphy.library.actions

import com.gamiphy.library.network.models.responses.redeem.Redeem

/**
 * A listener for Gamiphy bot redeem actions.
 */
interface OnRedeemTrigger {

    /**
     * Triggered whenever a red
     * eem action fired in Gamiphy bot -when redeem clicked in the bot-.
     * @param redeem: the redeem object want to redeem.
     */
    fun onRedeemTrigger(redeem: Redeem?)
}