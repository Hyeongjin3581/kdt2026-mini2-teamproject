package view;

/**
 * EquipmentView
 */
public class EquipmentView {

  private EquipmentView() {}

private static final EquipmentView instance
        = new EquipmentView();

public static EquipmentView getInstance() {
    return instance;
}

}
