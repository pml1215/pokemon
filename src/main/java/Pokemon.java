public class Pokemon {
    private int ID;
    private int blood;
    private int originalBlood;
    private int attack;
    private int defense;
    private String name;
    private Boolean isDefending = false;
    private Boolean isBuff = false;

    // Constructor
    public Pokemon(int ID, int blood, int attack, int defense, String name) {
        this.ID = ID;
        this.blood = blood;
        this.originalBlood = blood;
        this.attack = attack;
        this.defense = defense;
        this.name = name;
    }

    // Getter
    public String getName() {
        return name;
    }
    public int getBlood() {
        return blood;
    }

    // attack method
    public void attack(Pokemon rival){
        // used to calculate the attack damage
        int attackDamage = this.attack;
        String description = "";

        // Check whether the pokemon is buffed
        if (this.isBuff){
            attackDamage *= 2;
            this.isBuff = false;
        }

        // Check if rival is defending
        if (rival.isDefending){
            attackDamage  = 0;
            description = rival.name + " successfully defends the attack!";
            rival.isDefending = false;
        }
        else{
            // using random number to generate the attack result
            int random = (int) (Math.random() * 100);
            //30% chance to miss the attack
            if (random < 30){
                description = this.name + "'s attack missed!";
                attackDamage = 0;
            }
            // 20% chance to get critical hit
            else if (random > 30 && random < 50){
                description = "Critical Hit!";
                attackDamage = attackDamage * 2 - rival.defense;
            }
            // 10% chance to get not very effective attack
            else if (random > 50 && random < 60){
                description = "It's not very effective...";
                attackDamage = attackDamage / 2 - rival.defense;
            }
            // the remaining 40% chance to get normal attack
            else{
                description = "Normal Attack!";
                attackDamage = attackDamage - rival.defense;
            }

            // if attack damage is negative, set it to 0
            if (attackDamage < 0){
                attackDamage = 0;
            }
        }
        // if the attack damage is greater than rival's blood, set rival's blood to 0
        if (rival.blood - attackDamage < 0){
            rival.blood = 0;
        }
        else{
            // subtract the attack damage from rival's blood
            rival.blood -= attackDamage;
        }
        // print the attack result
        System.out.println(this.name + " used " + "attack!!");
        System.out.println(description);
        System.out.println(this.name + " attacks " + rival.name + " with " + attackDamage + " damage\n");
    }

    public void defense(){
        System.out.println(this.name + " used defense!!\n");
        // 50% chance to successfully defend the attack
        int random = (int)(Math.random() * 100);
        if (random > 50) {
            this.isDefending = true;
        }

        // check whether if the pokemon is buffed
        if (!this.isBuff){
            int random2 = (int)(Math.random() * 100);
            // 50% chance to get buffed for the next attack
            if (random2 > 50){
                this.isBuff = true;
                System.out.println(this.name + " is buffed for the next attack!!\n");
            }
        }
    }

    public void resetDefendStatus(){
        this.isDefending = false;
    }

    public void heal(){
        int healAmount;
        // if the pokemon's blood is less than 20, heal 40 HP
        if (this.blood < 20){
            healAmount = 40;
        }
        else{
            healAmount = 20;
        }
        // if the heal amount is greater than the original blood, set it to the original blood
        if (this.blood + healAmount > this.originalBlood){
            healAmount = this.originalBlood - this.blood;
        }
        this.blood += healAmount;
        System.out.println(this.name+" used heal!!");
        System.out.println(this.name+" Healed " + healAmount + " HP\n");
    }

    public Boolean checkAlive(){
        // check if the pokemon is alive
        if (this.blood <= 0){
            return false;
        }
        return true;
    }

}

