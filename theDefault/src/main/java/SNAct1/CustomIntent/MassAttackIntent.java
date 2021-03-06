package SNAct1.CustomIntent;

import actlikeit.RazIntent.CustomIntent;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import SNAct1.SNAct1Mod;

import static SNAct1.SNAct1Mod.makeUIPath;

public class MassAttackIntent extends CustomIntent {
    public static final String ID = SNAct1Mod.makeID("MassAttackIntent");

    private static final UIStrings uiStrings;
    private static final String[] TEXT;

    public MassAttackIntent() {
        super(IntentEnums.MASS_ATTACK, TEXT[0],
                makeUIPath("x_aoeIntent_L.png"),
                makeUIPath("x_aoeIntent.png"));
    }

    @Override
    public String description(AbstractMonster mo) {
        String result = TEXT[1];
        result += mo.getIntentDmg();
        result += TEXT[2];
        int hitCount;
        if ((Boolean) ReflectionHacks.getPrivate(mo, AbstractMonster.class, "isMultiDmg")) {
            hitCount = (Integer) ReflectionHacks.getPrivate(mo, AbstractMonster.class, "intentMultiAmt");
            result += " #b" + hitCount + TEXT[4];
        } else {
            result += TEXT[3];
        }

        return result;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(ID);
        TEXT = uiStrings.TEXT;
    }
}