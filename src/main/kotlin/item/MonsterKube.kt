package org.example.item

import org.example.joueur
import org.example.monstres.IndividuMonstre
import java.lang.Math.random
import org.example.dresseur.Entraineur

class MonsterKube(
    id: Int,
    nom: String,
    description: String,
    var chanceCapture: Double,
) : Item(id, nom, description), Utilisable {
    override fun utiliser(cible: IndividuMonstre): Boolean {
        //TODO("Not yet implemented")
        println("Vous lanccez le Monster Kube !")
        if (cible.entraineur != null) {
            println("Le monstre ne peut pas être capturé.")
        }
        else{
            val nbAleatoire = random()
            if(nbAleatoire < chanceCapture){
                println("Le monstre est capturé !")
                val nouveauNom = cible.renommer()
                if (nouveauNom != null)
                    cible.nom = nouveauNom.toString()
                if (joueur.equipeMonstre.size >= 6)
                    joueur.boiteMonstre.add(cible)
                else
                    joueur.equipeMonstre.add(cible)
                cible.entraineur = joueur
            }
            else{
                println("Presque ! Le kube n'a pas pu capturer le monstre !")
            }
        }
        return true
    }
}