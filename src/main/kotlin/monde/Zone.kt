package org.example.monde

import org.example.dresseur.Entraineur
import org.example.espece_Bugsyface
import org.example.espece_Galum
import org.example.espece_Laoumi
import org.example.jeu.CombatMonstre
import org.example.joueur
import org.example.monstres.EspeceMonstre
import org.example.monstres.IndividuMonstre
import kotlin.random.Random

class Zone(
    var id: Int,
    var nom: String,
    var expZone: Int,
    var especemonstres: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var zonePrecedente: Zone? = null
) {
    //TODO verifier la methode genereMonstre()
    //TODO verifier la methode rencontrerMonstre()
    fun genererMonstre(): IndividuMonstre {
//        especemonstres.add(espece_Bugsyface)
//        especemonstres.add(espece_Galum)
//        especemonstres.add(espece_Laoumi)
        val monstreAleatoire: EspeceMonstre = this.especemonstres.random()
        val monstre = IndividuMonstre(1, this.nom, monstreAleatoire, null, 0.0)
        monstre.exp += this.expZone * (20.0/100)
        //monstre.exp +-= this.expZone
        return monstre
    }

    val nomJoueur = readln().toString()
    fun rencontrerMonstre() {
        var monstreSauvage: IndividuMonstre = genererMonstre()
        var premierPokemon = joueur.equipeMonstre.find { it.pv > 0 }!!

        val combat = CombatMonstre(premierPokemon, monstreSauvage)
        combat.lanceCombat()
    }

}