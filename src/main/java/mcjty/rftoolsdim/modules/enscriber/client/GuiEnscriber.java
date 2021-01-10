package mcjty.rftoolsdim.modules.enscriber.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import mcjty.lib.container.GenericContainer;
import mcjty.lib.gui.GenericGuiContainer;
import mcjty.lib.gui.Window;
import mcjty.lib.gui.layout.HorizontalAlignment;
import mcjty.lib.gui.widgets.*;
import mcjty.lib.varia.RedstoneMode;
import mcjty.rftoolsbase.RFToolsBase;
import mcjty.rftoolsdim.RFToolsDim;
import mcjty.rftoolsdim.modules.enscriber.EnscriberSetup;
import mcjty.rftoolsdim.modules.enscriber.blocks.EnscriberTileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;

import static mcjty.lib.gui.widgets.Widgets.*;

public class GuiEnscriber extends GenericGuiContainer<EnscriberTileEntity, GenericContainer> {
    public static final int BUILDER_WIDTH = 180;
    public static final int BUILDER_HEIGHT = 152;

    private EnergyBar energyBar;
    private ImageLabel stages;
    private Label percentage;
    private Label error1;
    private Label error2;

    // @todo 1.15 fix!
    private static final ResourceLocation iconLocation = new ResourceLocation(RFToolsDim.MODID, "textures/gui/dimensionbuilder.png");
    private static final ResourceLocation iconStages = new ResourceLocation(RFToolsDim.MODID, "textures/gui/dimensionbuilderstages.png");
    private static final ResourceLocation iconGuiElements = new ResourceLocation(RFToolsBase.MODID, "textures/gui/guielements.png");

    public GuiEnscriber(EnscriberTileEntity tileEntity, GenericContainer container, PlayerInventory inventory) {
        super(tileEntity, container, inventory, EnscriberSetup.ENSCRIBER.get().getManualEntry());

        xSize = BUILDER_WIDTH;
        ySize = BUILDER_HEIGHT;
    }

    @Override
    public void init() {
        super.init();

        energyBar = new EnergyBar().vertical().hint(10, 7, 8, 54).showText(false);
        stages = new ImageLabel().image(iconStages, 0, 0).hint(61, 9, 48, 48);

        percentage = label(115, 25, 50, 16, "0%").horizontalAlignment(HorizontalAlignment.ALIGN_LEFT);
        error1 = label(115, 15, 60, 16, "").horizontalAlignment(HorizontalAlignment.ALIGN_LEFT).color(0xff0000);
        error2 = label(115, 28, 60, 16, "").horizontalAlignment(HorizontalAlignment.ALIGN_LEFT).color(0xff0000);

        ImageChoiceLabel redstoneMode = initRedstoneMode();

        Panel toplevel = positional().background(iconLocation).children(energyBar, stages, percentage, error1, error2, redstoneMode);
        toplevel.bounds(guiLeft, guiTop, xSize, ySize);

        window = new Window(this, toplevel);
//        tileEntity.requestBuildingPercentage();

        // @todo
//        window.bind(RFToolsDimMessages.INSTANCE, "redstone", tileEntity, GenericTileEntity.VALUE_RSMODE.getName());
    }

    private ImageChoiceLabel initRedstoneMode() {
        return imageChoice(150, 46, 16, 16).
                name("redstone").
                choice(RedstoneMode.REDSTONE_IGNORED.getDescription(), "Redstone mode:\nIgnored", iconGuiElements, 0, 0).
                choice(RedstoneMode.REDSTONE_OFFREQUIRED.getDescription(), "Redstone mode:\nOff to activate", iconGuiElements, 16, 0).
                choice(RedstoneMode.REDSTONE_ONREQUIRED.getDescription(), "Redstone mode:\nOn to activate", iconGuiElements, 32, 0);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
//        int pct = DimensionBuilderTileEntity.getBuildPercentage();
//
//        if (pct == DimensionBuilderTileEntity.ERROR_NOOWNER) {
//            error1.setText("Builder has");
//            error2.setText("no owner!");
//            percentage.setText("");
//        } else if (pct == DimensionBuilderTileEntity.ERROR_TOOMANYDIMENSIONS) {
//            error1.setText("Too many");
//            error2.setText("dimensions!");
//            percentage.setText("");
//        } else {
//            int x = ((pct - 1) / 4) % 5;
//            int y = ((pct - 1) / 4) / 5;
//            stages.setImage(iconStages, x * 48, y * 48);
//            percentage.setText(pct + "%");
//            error1.setText("");
//            error2.setText("");
//        }

        drawWindow(matrixStack);

//        energyBar.setValue(GenericEnergyStorageTileEntity.getCurrentRF());
//
//        tileEntity.requestRfFromServer(RFToolsDim.MODID);
//        tileEntity.requestBuildingPercentage();
    }
}
