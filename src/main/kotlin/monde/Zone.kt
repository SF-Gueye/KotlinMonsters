package org.example.monde

import org.example.monstres.EspeceMonstre

class Zone(
    var id: Int,
    var nom: String,
    var expZone: Int,
    var especemonstres: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var zonePrecedente: Zone? = null
) {
    //TODO faire la methode genereMonstre()
    //TODO faire la methode rencontrerMonstre()

}