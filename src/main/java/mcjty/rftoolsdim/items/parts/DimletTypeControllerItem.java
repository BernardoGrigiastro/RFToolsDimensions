package mcjty.rftoolsdim.items.parts;

import mcjty.rftoolsdim.RFToolsDim;
import mcjty.rftoolsdim.dimensions.dimlets.types.DimletType;
import mcjty.rftoolsdim.items.GenericRFToolsItem;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DimletTypeControllerItem extends GenericRFToolsItem {

    public DimletTypeControllerItem() {
        super("dimlet_type_controller");
        setMaxStackSize(64);
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        Map<DimletType,ModelResourceLocation> models = new HashMap<>();
        for (DimletType type : DimletType.values()) {
            models.put(type, new ModelResourceLocation(getRegistryName() + calculateUnlocalizedNameSuffix(type), "inventory"));
            ModelBakery.registerItemVariants(this, models.get(type));
        }

        ModelLoader.setCustomMeshDefinition(this, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return models.get(DimletType.values()[stack.getItemDamage()]);
            }
        });
    }

    private String calculateUnlocalizedNameSuffix(DimletType type) {
        return "_" + StringUtils.uncapitalize(type.dimletType.getName());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> list, boolean whatIsThis) {
        super.addInformation(itemStack, player, list, whatIsThis);
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            list.add(TextFormatting.WHITE + "Every dimlet needs a type specific controller. You can");
            list.add(TextFormatting.WHITE + "get this by deconstructing other dimlets in the Dimlet");
            list.add(TextFormatting.WHITE + "Workbench. In that same workbench you can also use");
            list.add(TextFormatting.WHITE + "this item to make new dimlets.");
        } else {
            list.add(TextFormatting.WHITE + RFToolsDim.SHIFT_MESSAGE);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName(itemStack) + calculateUnlocalizedNameSuffix(DimletType.values()[itemStack.getItemDamage()]);
    }

    @Override
    protected void clGetSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (DimletType type : DimletType.values()) {
            subItems.add(new ItemStack(this, 1, type.ordinal()));
        }
    }
}
