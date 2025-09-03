const FIRST_CALL = 'first_call_done'
PlayerEvents.tick(e => {
  const p = e.player
  if (p.persistentData[FIRST_CALL]) return
  if (p.level.dimension != 'minecraft:the_end') return
  if (!p.persistentData[INTRO_DONE]) return
  if (!p.vehicle) return
  p.persistentData[FIRST_CALL] = true
  e.server.runCommandSilent('execute as ' + p.username + ' run chatbox skip intro_dialog:radio radio_disabled 0')

  e.server.scheduleInTicks(10 * 20, () => {
    e.server.runCommandSilent('execute as ' + p.username + ' run chatbox skip intro_dialog:radio radio_disabled 1')
  })

  e.server.scheduleInTicks(15 * 20, () => {
    e.server.runCommandSilent('execute as ' + p.username + ' run chatbox skip intro_dialog:radio radio_disabled 2')
  })
})