package me.calebbassham.skyhigh

import me.calebbassham.scenariomanager.api.*

class SkyHigh : Scenario("SkyHigh") {

    val time = TimeSpanScenarioSetting("Time", "The time at which players are affected by SkyHigh.", 30 * 60 * 20)
    val interval = TimeSpanScenarioSetting("Interval", "The amount of time between damaging players.", 30 * 20)
    val height = SimpleScenarioSetting("Height", "How high the player must be to not take damage.", 100)
    val damage = SimpleScenarioSetting("Damage", "How much damage to do to the player.", 1)

    override val settings = listOf(time, interval, height, damage)

    override val description: String
        get() = "After ${time.displayValue()}, all players below y ${height.displayValue()} will take ${damage.value.toDouble() / 2} heart of damage every ${interval.displayValue()}."

    override fun onScenarioStart() {
        scheduleEvent(Event(false), time.value.ticks, true)
    }

    private inner class Event(hide: Boolean = true) : ScenarioEvent("Sky High", hide) {
        override fun run() {
            scenarioManager.gamePlayerProvider.gamePlayers
                .filter { it.location.blockY < height.value }
                .forEach { it.damage(damage.value.toDouble()) }

            scheduleEvent(Event(), interval.value.ticks)
        }
    }

}