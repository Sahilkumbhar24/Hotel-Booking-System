import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


class Room {
    private int roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean isBooked;

    public Room(int roomNumber, String roomType, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isBooked = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookRoom() {
        isBooked = true;
    }

    public void freeRoom() {
        isBooked = false;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + roomType + ") - $" + pricePerNight + "/night";
    }
}


public class HotelBookingSystem {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static JTextArea roomDisplayArea;

    public static void main(String[] args) {
     
        initializeRooms();

      
        JFrame frame = new JFrame("Hotel Booking System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

      
        roomDisplayArea = new JTextArea();
        roomDisplayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(roomDisplayArea);
        frame.add(scrollPane, BorderLayout.CENTER);

       
        JPanel buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);

      
        JButton viewRoomsButton = new JButton("View Rooms");
        JButton bookRoomButton = new JButton("Book Room");
        JButton checkoutButton = new JButton("Check-Out");
        JButton exitButton = new JButton("Exit");

        buttonPanel.add(viewRoomsButton);
        buttonPanel.add(bookRoomButton);
        buttonPanel.add(checkoutButton);
        buttonPanel.add(exitButton);

       
        viewRoomsButton.addActionListener(e -> displayRooms());
        bookRoomButton.addActionListener(e -> bookRoom());
        checkoutButton.addActionListener(e -> checkOut());
        exitButton.addActionListener(e -> System.exit(0));


        frame.setVisible(true);
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Single", 50.0));
        rooms.add(new Room(102, "Single", 50.0));
        rooms.add(new Room(201, "Double", 75.0));
        rooms.add(new Room(202, "Double", 75.0));
        rooms.add(new Room(301, "Suite", 150.0));
    }

 
    private static void displayRooms() {
        StringBuilder display = new StringBuilder();
        for (Room room : rooms) {
            display.append(room.toString())
                    .append(" - ")
                    .append(room.isBooked() ? "Booked" : "Available")
                    .append("\n");
        }
        roomDisplayArea.setText(display.toString());
    }


    private static void bookRoom() {
        String input = JOptionPane.showInputDialog("Enter Room Number to Book:");
        if (input == null || input.isEmpty()) return;

        int roomNumber = Integer.parseInt(input);
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                if (room.isBooked()) {
                    JOptionPane.showMessageDialog(null, "Room is already booked!");
                    return;
                }
                room.bookRoom();
                JOptionPane.showMessageDialog(null, "Room " + roomNumber + " has been booked!");
                displayRooms();
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Invalid Room Number!");
    }

    
    private static void checkOut() {
        String input = JOptionPane.showInputDialog("Enter Room Number to Check-Out:");
        if (input == null || input.isEmpty()) return;

        int roomNumber = Integer.parseInt(input);
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                if (!room.isBooked()) {
                    JOptionPane.showMessageDialog(null, "Room is not booked!");
                    return;
                }
                room.freeRoom();
                JOptionPane.showMessageDialog(null, "Room " + roomNumber + " has been checked out!");
                displayRooms();
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Invalid Room Number!");
    }
}
