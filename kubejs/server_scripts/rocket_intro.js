const INTRO_DONE = 'rocket_intro_done'
const INTRO_SPAWN_POS = 'rocket_intro_spawn_pos'
PlayerEvents.tick(e => {
  const p = e.player
  if (p.persistentData[INTRO_DONE]) return
  if (p.level.dimension != 'minecraft:the_end') return
  const {x, y, z} = p.blockPosition()
  p.persistentData[INTRO_SPAWN_POS] = {x: x, y: y, z: z}
  const yRocket = 300
  const yPlayer   = yRocket + 5
  p.teleportTo(x, yPlayer, z)               //seconds
  p.potionEffects.add('minecraft:resistance', 60 * 20, 255, false, false)
  const rocket = p.level.createEntity('ad_astra:rocket_lander')
  rocket.setPos(x, yRocket, z)
  rocket.spawn()
  p.startRiding(rocket)
  rocket.setPos(x + 0.1, yRocket, z + 0.1)
  p.persistentData[INTRO_DONE] = true
})