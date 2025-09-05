package net.dercoolin.archaeologyplus.block.custom;

import net.minecraft.server.world.ServerWorld;

public class WeatherController {
    public static void setClearWeather(ServerWorld world, int durationTicks) {
        // Stop rain and thunder
        world.setWeather(durationTicks, 0, false, false);
        // Alternative method:
        // world.getGameRules().get(GameRules.DO_WEATHER_CYCLE).set(false, world.getServer());
        // world.setRainGradient(0.0f);
        // world.setThunderGradient(0.0f);
    }

    /**
     * Set weather to rain (without thunder)
     */
    public static void setRainyWeather(ServerWorld world, int durationTicks) {
        world.setWeather(0, durationTicks, true, false);
    }

    /**
     * Set weather to thunderstorm
     */
    public static void setThunderstorm(ServerWorld world, int durationTicks) {
        world.setWeather(0, durationTicks, true, true);
    }
}
