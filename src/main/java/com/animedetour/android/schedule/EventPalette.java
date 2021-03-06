/*
 * This file is part of the Anime Detour Android application
 *
 * Copyright (c) 2015 Anime Twin Cities, Inc.
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.animedetour.android.schedule;

import android.support.annotation.ColorRes;
import com.animedetour.android.R;
import monolog.Monolog;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;

/**
 * Create a color to represent an event based on the type.
 *
 * This tries to figure out the type of the event and assigns a color that
 * can be used to identify/categorize that type.
 * The types here are hardcoded so that they can be assigned in a way that
 * matches the color coding between apps and on sched.org.
 *
 * @todo Abstract this in some way so that the types aren't hardcoded.
 * @author Maxwell Vandervelde (Max@MaxVandervelde.com)
 */
@Singleton
public class EventPalette
{
    final private Monolog logger;

    /** Labels we've encountered that aren't recognized. */
    final private ArrayList<String> unknownLabels = new ArrayList<>();

    /** colors that can be used if an event without an identifiable type is found. */
    final private static int[] unknowns = new int[] {
        R.color.label_unknown2,
        R.color.label_unknown3,
        R.color.label_unknown4,
    };

    /** colors that can be used if an event without an identifiable type is found. */
    final private static int[] unknownDims = new int[] {
        R.color.label_unknown2_dim,
        R.color.label_unknown3_dim,
        R.color.label_unknown4_dim,
    };

    @Inject
    public EventPalette(Monolog logger)
    {
        this.logger = logger;
    }

    /**
     * Get a color unique to the string type.
     *
     * Tries to match the type to a known type and color for that. If it fails
     * it will assign an unknown color for that type. That unknown color will
     * be the same for subsequent calls of the same type.
     *
     * @param type The type to match and get a color for.
     * @return The color to identify that type.
     */
    @ColorRes
    final public int getColor(String type)
    {
        if (type.equals("Hours of Operation")) {
            return R.color.label_hours;
        }

        if (type.equals("Panel")) {
            return R.color.label_panel;
        }

        if (type.equals("Electronic Gaming")) {
            return R.color.label_videogame;
        }

        if (type.equals("Event")) {
            return R.color.label_event;
        }

        if (type.equals("Guest Signing")) {
            return R.color.label_guestsigning;
        }

        if (type.equals("Tabletop Gaming")) {
            return R.color.label_tabletopgame;
        }

        if (type.equals("Video")) {
            return R.color.label_video;
        }

        if (type.equals("Cosplay Photoshoot")) {
            return R.color.label_photoshoot;
        }

        if (type.equals("Workshop")) {
            return R.color.label_workshop;
        }

        if (type.equals("Room Party")) {
            return R.color.label_roomparty;
        }

        return this.getUnknowncolor(type);
    }

    /**
     * Get a darker color unique to the string type.
     *
     * Tries to match the type to a known type and color for that. If it fails
     * it will assign an unknown color for that type. That unknown color will
     * be the same for subsequent calls of the same type.
     *
     * @param type The type to match and get a color for.
     * @return The color to identify that type.
     */
    @ColorRes
    final public int getDimColor(String type)
    {
        if (type.equals("Hours of Operation")) {
            return R.color.label_hours_dim;
        }

        if (type.equals("Panel")) {
            return R.color.label_panel_dim;
        }

        if (type.equals("Electronic Gaming")) {
            return R.color.label_videogame_dim;
        }

        if (type.equals("Event")) {
            return R.color.label_event_dim;
        }

        if (type.equals("Guest Signing")) {
            return R.color.label_guestsigning_dim;
        }

        if (type.equals("Tabletop Gaming")) {
            return R.color.label_tabletopgame_dim;
        }

        if (type.equals("Video")) {
            return R.color.label_video_dim;
        }

        if (type.equals("Cosplay Photoshoot")) {
            return R.color.label_photoshoot_dim;
        }

        if (type.equals("Workshop")) {
            return R.color.label_workshop_dim;
        }

        if (type.equals("Room Party")) {
            return R.color.label_roomparty_dim;
        }

        return this.getDimUnknowncolor(type);
    }

    /**
     * Get a color to identify an type.
     *
     * This will register the color so that it always returns the same color for
     * each unique value.
     * If we run out of colors, this will loop back around to the start, but
     * will log a warning.
     *
     * @param type The type to match and get a color for.
     * @return The color to identify that type.
     */
    @ColorRes
    private int getUnknowncolor(String type)
    {
        if (false == this.unknownLabels.contains(type)) {
            this.unknownLabels.add(type);
        }
        int index = this.unknownLabels.indexOf(type);

        if (index > this.unknownLabels.size()) {
            this.logger.warn(
                "Ran out of colors for palette. Will recycle old color"
            );
        }

        return unknowns[index % unknownLabels.size()];
    }

    /**
     * Get a darker color to identify an type.
     *
     * This will register the color so that it always returns the same color for
     * each unique value.
     * If we run out of colors, this will loop back around to the start, but
     * will log a warning.
     *
     * @param type The type to match and get a color for.
     * @return The color to identify that type.
     */
    @ColorRes
    private int getDimUnknowncolor(String type)
    {
        if (false == this.unknownLabels.contains(type)) {
            this.unknownLabels.add(type);
        }
        int index = this.unknownLabels.indexOf(type);

        if (index > this.unknownLabels.size()) {
            this.logger.warn(
                "Ran out of colors for palette. Will recycle old color"
            );
        }

        return unknownDims[index % unknownLabels.size()];
    }
}
