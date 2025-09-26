package org.example.monstres
import com.sun.tools.javac.jvm.ByteCodes.breakpoint
import kotlin.math.pow
import org.example.dresseur.Entraineur
import org.example.espece_Aquamy
import org.example.joueur
import java.io.File
import kotlin.random.Random

val variation = mutableListOf(-2, 2)
val variationpv = mutableListOf(-5, 5)

class IndividuMonstre(
    var id: Int,
    var nom: String,
    var espece: EspeceMonstre,
    var entraineur: Entraineur? = null,
    explnit: Double
) {
    var ran = Random.nextInt(variation.first(), variation.last() + 1)
    var ranpv = Random.nextInt(variationpv.first(), variationpv.last() + 1)

    var niveau: Int = 1
    var attaque: Int = espece.baseAttaque + ran
    var defense: Int = espece.baseDefense + ran
    var vitesse: Int = espece.baseVitesse + ran
    var attaquespe: Int = espece.baseAttaqueSpe + ran
    var defensespe: Int = espece.baseDefenseSpe + ran
    var pvMax: Int = espece.basePv + ranpv
    var potentiel: Double = 0.5

    var exp: Double = 0.0
        get() = field
        set(value) {
            field=value
            var estNiveau1=false
            if(this.niveau==1){
                estNiveau1=true
            }
            while (field >= palierExp(this.niveau)) {
                levelUp()
            }

            if(estNiveau1 == false){
                println("Le monstre ${this.nom} est maintenant niveau ${this.niveau} !")
            }
        }

    /**
     *  @property pv  Points de vie actuels.
     * Ne peut pas être inférieur à 0 ni supérieur à [pvMax].
     */
    var  pv: Int = pvMax
        get() = field
        set(nouveauPv) {
            field=nouveauPv
        }
    /**
     * Calcule l'expérience totale nécessaire pour atteindre un niveau donné.
     *
     * @param niveau Niveau cible.
     * @return Expérience cumulée nécessaire pour atteindre ce niveau.
     */
    fun palierExp(niveau: Int): Int {
        val expo = 2.0
        val expCalcule = 100 * ((niveau - 1).toDouble().pow(expo)).toInt()
        return expCalcule
    }

    fun levelUp(){
        this.niveau++
        this.attaque += ((this.espece.modAttaque * this.potentiel) + this.ran).toInt()
        this.defense += ((this.espece.modDefense * this.potentiel) + this.ran).toInt()
        this.vitesse += ((this.espece.modVitesse * this.potentiel) + this.ran).toInt()
        this.attaquespe += ((this.espece.modAttaqueSpe * this.potentiel) + this.ran).toInt()
        this.defensespe += ((this.espece.modDefenseSpe * this.potentiel) + this.ran).toInt()
        this.pvMax += ((this.espece.modPv * this.potentiel) + this.ranpv).toInt()
        this.pv = this.pvMax
    }
    init {
        this.exp = explnit // applique le setter et déclanche un évantuel level-up
    }

    fun attaquer() {
        /**
         * Attaque un autre [IndividuMonstre] et inflige des dégâts.
         *
         * Les dégâts sont calculés de manière très simple pour le moment :
         * `dégâts = attaque - (défense / 2)` (minimum 1 dégât).
         *
         * @param cible Monstre cible de l'attaque.
         */
        val cible = IndividuMonstre(3, "aquamy", espece_Aquamy, joueur, 1500.0)
        val degatsBrut = this.attaque
        var degatsTotal = degatsBrut - (this.defense / 2)
        if (degatsTotal < 1) {
            degatsTotal = 1
        }
        val pvAvant = cible.pv
        cible.pv -= degatsTotal
        val pvApres = cible.pv
        val nbdedegats = pvAvant - pvApres
        println("${this.nom} inflige ${nbdedegats} dégâts à ${cible.nom}")
    }

    fun renommer(){
        /**
         * Demande au joueur de renommer le monstre.
         * Si l'utilisateur entre un texte vide, le nom n'est pas modifié.
         */
        print("Renommer le monstre ${this.nom} : ")
        val nom = readln()
        if (nom.isNotEmpty()) {
            this.nom = nom
        }
    }

//    fun afficheArt(deFace: Boolean =true): String{
//        val nomFichier = if(deFace) "front" else "back";
//        val art= File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
//        val safeArt = art.replace("/", "∕")
//        return safeArt.replace("\\u001B", "\u001B")
//    }

    fun afficheDetail(){
        println("=========================")
        print("Nom : ${this.nom}  ")
        println("Niveau : ${this.niveau}")
        println("Exp: ${this.exp}")
        println("PV: ${this.pv}/${this.pvMax}")
        println("=========================")
        print("Atq : ${this.attaque}    ")
        print("Def : ${this.defense}    ")
        println("Vit : ${this.vitesse}  ")
        print("AtqSpe : ${this.attaquespe}    ")
        println("DefSpe : ${this.defensespe}  ")
        println("=========================")
    }
}