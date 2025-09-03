const RIDE_PEARLS_TRIGGERED = 'kubejs:ride_pearls_triggered'

PlayerEvents.loggedIn(e => {
  const p = e.player
  if (p.persistentData[RIDE_PEARLS_TRIGGERED]) return

    e.server.runCommandSilent(`scoreboard players set ${p.name.string} RidePearls 1`)
    e.server.runCommandSilent(`give ${p.name.string} minecraft:ender_pearl`)
  p.persistentData[RIDE_PEARLS_TRIGGERED] = true
})