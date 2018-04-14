/*
 * Copyright (c) 2018 Hubry
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package hubry.landings;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static hubry.landings.HappyLandings.*;

@Mod.EventBusSubscriber(modid = MODID)
@Mod(modid = MODID, name = NAME, version = VERSION, acceptedMinecraftVersions = "[1.12,1.13)")
public class HappyLandings {

    public static final String MODID = "happylandings";
    public static final String NAME = "Happy Landings";
    public static final String VERSION = "1.1";
    
    @SubscribeEvent
    public static void cancelElytraWallDamage(LivingAttackEvent event) {
        if (event.getEntity() instanceof EntityPlayer && Configs.isElytraDamageCancelled && event.getSource() == DamageSource.FLY_INTO_WALL) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void cancelFallDamage(LivingFallEvent event) {
        if (event.getEntity() instanceof EntityPlayer && Configs.isFallDamageCancelled) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(MODID)) {
            ConfigManager.sync(MODID, Config.Type.INSTANCE);
        }
    }

}
