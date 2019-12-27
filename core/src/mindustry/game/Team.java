package mindustry.game;

import arc.*;
import arc.graphics.*;
import arc.util.*;
import mindustry.graphics.*;

public class Team implements Comparable<Team>{
    public final byte id;
    public final Color color;
    public String name;

    /** All 256 registered teams. */
    private static final Team[] all = new Team[256];
    /** The 6 base teams used in the editor. */
    private static final Team[] baseTeams = new Team[6];

    public final static Team
        derelict = new Team(0, "derelict", Color.valueOf("4d4e58")),
        sharded = new Team(1, "sharded", Pal.accent.cpy()),
        crux = new Team(2, "crux", Color.valueOf("e82d2d")),
        green = new Team(3, "green", Color.valueOf("4dd98b")),
        purple = new Team(4, "purple", Color.valueOf("9a4bdf")),
        blue = new Team(5, "blue", Color.royal.cpy());

    static{
        //create the whole 256 placeholder teams
        for(int i = 6; i < all.length; i++){
            new Team(i, "team#" + i, Color.HSVtoRGB(360f * (float)(i) / all.length, 100f, 100f, 1f));
        }
    }

    public static Team get(int id){
        return all[Pack.u((byte)id)];
    }

    /** @return the 6 base team colors. */
    public static Team[] base(){
        return baseTeams;
    }

    /** @return all the teams - do not use this for lookup! */
    public static Team[] all(){
        return all;
    }

    protected Team(int id, String name, Color color){
        this.name = name;
        this.color = color;
        this.id = (byte)id;

        int us = Pack.u(this.id);
        if(us < 6) baseTeams[us] = this;
        all[us] = this;
    }

    public String localized(){
        return Core.bundle.get("team." + name + ".name", name);
    }

    @Override
    public int compareTo(Team team){
        return Integer.compare(id, team.id);
    }
}
