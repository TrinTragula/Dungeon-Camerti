/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.watabou.pixeldungeon.levels.features;

import com.watabou.noosa.audio.Sample;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.effects.CellEmitter;
import com.watabou.pixeldungeon.effects.particles.ElmoParticle;
import com.watabou.pixeldungeon.levels.DeadEndLevel;
import com.watabou.pixeldungeon.levels.Terrain;
import com.watabou.pixeldungeon.scenes.GameScene;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.pixeldungeon.windows.WndMessage;

public class Sign {

	private static final String TXT_DEAD_END = 
		"Come diavolo sei finito qui?!";
	
	private static final String[] TIPS = {
		"Non ascoltare Martino. c'e' speranza, ma non sovrastimare la tua intelligenza.",
		"Non tutte le porte del dipartimento sono visibili. Collassa funzioni d'onda per rivelarle.",
		"Non devi per forza aumentare la tua intelligenza per usare armi migliori. Puoi sempre" +
			"fare il contrario e rendere piu' stupide le armi con le magie.",
		"Puoi spendere le tue equazioni nei negozi che troverai dal sesto dipartimento in poi",
			
		"Attento allo sperma di Martino!",
		
		"Pixel-Mart - tutto quello di cui hai bisogno per la tua avventura!",
		"Identifica le tue cose non appena puoi, non stare ad aspettare  " +
			"il momento migliore.",
		"Avere fame di meme non fa male, ma desiderarli troppo si'.",
		"Gli attacchi a sorpresa fanno un male cane. Attira i nemici ed aspettali dietro una porta  " +
			"per menarli forte sui denti.",
		
		"Non far scappare il Tengu!",
		
		"Pixel-Mart. Spendi equazioni, vivi a lungo.",
		"Se troppa roba cerca di strapparti i capelli, rifugiati dietro una porta.",
		"Se lieviti non puoi spegnerti sulle pozzanghere, duh.",
		"Non ha senso possedere piu' di un'essenza di Stizza, tanto verranno distrutte non appena risorgerai.",
		
		"ATTENZIONE! Prestare attenzione alle macchine cattive.",
		
		"Pixel-Mart. Una vita piu' Simonucci-like nel dungeon.",
		"Se upgradi un'arma incantata, potresti distruggere l'incantamento.",
		"Le armi e le armature si deteriorano piu' velocemente degli psicofarmaci, ma ci sono piu' modi di aggiustarli.",
		"L'unico modo di ottenere una wipe out scroll e' di chiederla a degli spiriti strani.",
		
		"Nessun arma e' permessa in presenza di Sua Maesta'!",
		
		"Pixel-Mart. Prezzi speciali per cacciatori di Accia!"
	};
	
	private static final String TXT_BURN =
		"Appena cerchi di leggere il cartello, quello brucia malissimo tra fiamme verdi.";
	
	public static void read( int pos ) {
		
		if (Dungeon.level instanceof DeadEndLevel) {
			
			GameScene.show( new WndMessage( TXT_DEAD_END ) );
			
		} else {
			
			int index = Dungeon.depth - 1;
			
			if (index < TIPS.length) {
				GameScene.show( new WndMessage( TIPS[index] ) );
			} else {
				
				Dungeon.level.destroy( pos );
				GameScene.updateMap( pos );
				GameScene.discoverTile( pos, Terrain.SIGN );
				
				CellEmitter.get( pos ).burst( ElmoParticle.FACTORY, 6 );
				Sample.INSTANCE.play( Assets.SND_BURNING );
				
				GLog.w( TXT_BURN );
				
			}
		}
	}
}
