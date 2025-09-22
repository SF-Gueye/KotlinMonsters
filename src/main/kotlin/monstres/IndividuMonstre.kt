package org.example.monstres
import com.sun.tools.javac.jvm.ByteCodes.breakpoint
import kotlin.math.pow
import org.example.dresseur.Entraineur
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
            if (field >= palierExp(this.niveau)) {
                levelUp()
            }
            if(estNiveau1 == false){
                println("${this.nom} est passé au niveau ${this.niveau} !")
            }
        }
    init {
        this.exp = explnit // applique le setter et déclanche un évantuel level-up
    }
    /**
     *  @property pv  Points de vie actuels.
     * Ne peut pas être inférieur à 0 ni supérieur à [pvMax].
     */
    var pv: Int = pvMax
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
        val calcul = 100 * (niveau - 1)
        val expo = 2.5
        return (calcul.toDouble().pow(expo)).toInt()
    }

    fun levelUp(){
        this.attaque += ((this.espece.modAttaque * this.potentiel) + this.ran).toInt()
        this.defense += ((this.espece.modDefense * this.potentiel) + this.ran).toInt()
        this.vitesse += ((this.espece.modVitesse * this.potentiel) + this.ran).toInt()
        this.attaquespe += ((this.espece.modAttaqueSpe * this.potentiel) + this.ran).toInt()
        this.defensespe += ((this.espece.modDefenseSpe * this.potentiel) + this.ran).toInt()
        this.pvMax += ((this.espece.modPv * this.potentiel) + this.ranpv).toInt()
        this.exp -= palierExp(this.niveau)
        this.niveau++
    }
}