import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RadioButtonDemo extends JFrame implements ActionListener {

    // Components
    private JRadioButton birdButton, catButton, dogButton, rabbitButton, pigButton;
    private JLabel imageLabel;
    private ButtonGroup group;

    public RadioButtonDemo() {
        // 1. Setup the Frame
        setTitle("RadioButtonDemo");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Add some padding

        // 2. Create the Radio Buttons
        birdButton = new JRadioButton("Bird");
        catButton = new JRadioButton("Cat");
        dogButton = new JRadioButton("Dog");
        rabbitButton = new JRadioButton("Rabbit");
        pigButton = new JRadioButton("Pig");

        // 3. Group the buttons so only one can be selected at a time
        group = new ButtonGroup();
        group.add(birdButton);
        group.add(catButton);
        group.add(dogButton);
        group.add(rabbitButton);
        group.add(pigButton);

        // Set "Pig" as default (as seen in screenshot)
        pigButton.setSelected(true);

        // 4. Add Action Listeners
        birdButton.addActionListener(this);
        catButton.addActionListener(this);
        dogButton.addActionListener(this);
        rabbitButton.addActionListener(this);
        pigButton.addActionListener(this);

        // 5. Create a Panel for the Radio Buttons (Left Side)
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(5, 1)); // Stack vertically
        radioPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
        radioPanel.add(birdButton);
        radioPanel.add(catButton);
        radioPanel.add(dogButton);
        radioPanel.add(rabbitButton);
        radioPanel.add(pigButton);

        // 6. Create the Image Panel (Right Side)
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        // Load default image (Pig) on startup
        updateImage("Pig");

        // 7. Add panels to the frame
        add(radioPanel, BorderLayout.WEST);
        add(imageLabel, BorderLayout.CENTER);
    }

    // Helper method to load images
    private void updateImage(String petName) {
        // In a real app, ensure these image files exist in your project root
        String fileName = petName.toLowerCase() + ".jpg"; 
        
        try {
            ImageIcon icon = new ImageIcon(fileName);
            // Check if image exists, otherwise create a placeholder
            if (icon.getIconWidth() == -1) {
                throw new Exception("Image not found");
            }
            // Scale image to fit
            Image img = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
            imageLabel.setText("");
        } catch (Exception e) {
            // Fallback if image is missing
            imageLabel.setIcon(null);
            imageLabel.setText("<html><center>Image for " + petName + " not found.<br>Add " + fileName + " to project folder.</center></html>");
            imageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        }
    }

    // Handle Button Clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedPet = "";

        if (birdButton.isSelected()) selectedPet = "Bird";
        else if (catButton.isSelected()) selectedPet = "Cat";
        else if (dogButton.isSelected()) selectedPet = "Dog";
        else if (rabbitButton.isSelected()) selectedPet = "Rabbit";
        else if (pigButton.isSelected()) selectedPet = "Pig";

        // Update the image
        updateImage(selectedPet);

        // Display the Message Box (Requirement from prompt)
        JOptionPane.showMessageDialog(this, "You selected: " + selectedPet);
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new RadioButtonDemo().setVisible(true);
        });
    }
}