package Main.WorldLogic;

import Main.MathHelper;
import Main.ObjectLogic.Decorations.Decoration;

import java.util.ArrayList;
import java.util.List;

public class ObjectSource
{
    private float spawnChance;
    private Decoration decor;
    private List<ObjectSource> children = new ArrayList<>();
    private ObjectSource parent;

    public ObjectSource(String[] source)
    {
        instantiate(source);
    }
    public ObjectSource(ObjectSource parent, String[] source)
    {
        this.parent = parent;
        instantiate(source);
    }
    private void instantiate(String[] source)
    {
        decor = new Decoration(source[1]);
        spawnChance = Float.parseFloat(source[2]);
    }

    public List<Decoration> spawnDecorations()
    {
        List<Decoration> result = new ArrayList<>();
        if (MathHelper.randomDecider(spawnChance)) //Get spawn chance
        {
            result.add(decor.copy());
        }
        else
        {
            for (ObjectSource child:children)
            {
                List<Decoration> childRes = child.spawnDecorations();
                for (Decoration decor:childRes)
                {
                    result.add(decor);
                }
            }
        }
        return result;
    }

    public ObjectSource getParent() {return parent;}
    public void addChild(ObjectSource child) {children.add(child);}
}
