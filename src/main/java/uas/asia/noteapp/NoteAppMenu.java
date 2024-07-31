/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas.asia.noteapp;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author budi
 */

public class NoteAppMenu {
    private NoteService noteService;
    private Scanner scanner;

    public NoteAppMenu(String databasePath) {
        noteService = new NoteService(new DatabaseStorage(databasePath));
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\nNote App Menu: Oleh Reyshandy Darma Fitra (23201019)");
            System.out.println("1. Tambah Catatan");
            System.out.println("2. Tampilkan Catatan");
            System.out.println("3. Hapus Catatan");
            System.out.println("4. Keluar");
            System.out.print("Pilih opsi: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addNote();
                        break;
                    case 2:
                        showNotes();
                        break;
                    case 3:
                        deleteNote();
                        break;
                    case 4:
                        System.out.println("Keluar...");
                        noteService.close();
                        return;
                    default:
                        System.out.println("Pilihan tidak valid. Coba lagi.");
                }
            } else {
                System.out.println("Input harus berupa angka.");
                scanner.next();
            }
        }
    }

    private void addNote() {
        System.out.print("Masukkan catatan: ");
        if(scanner.hasNextInt()){
            scanner.nextLine();
        }
        String note = scanner.nextLine();
        noteService.createNote(note);
        System.out.println("Catatan disimpan: " + note);
    }

    private void showNotes() {
        List<String> notes = noteService.readNotes();
        System.out.println("Catatan tersimpan:");
        if (notes.isEmpty()) {
            System.out.println("Tidak ada catatan.");
        } else {
            for (String note : notes) {
                System.out.println(note);
            }
        }
    }

    private void deleteNote() {
        List<String> notes = noteService.readNotes();
        if (notes.isEmpty()) {
            System.out.println("Tidak ada catatan untuk dihapus.");
            return;
        }

        showNotes();
        System.out.print("Masukkan nomor catatan yang akan dihapus: ");
        if (scanner.hasNextInt()) {
            int index = scanner.nextInt() - 1;
            scanner.nextLine();  // Clear the buffer
            if (index >= 0 && index < notes.size()) {
                String noteToDelete = notes.get(index);
                noteService.deleteNote(noteToDelete);
                System.out.println("Catatan dihapus: " + noteToDelete);
            } else {
                System.out.println("Nomor catatan tidak valid.");
            }
        } else {
            System.out.println("Input harus berupa angka.");
            scanner.next();  // Clear the invalid input
        }
    }
}
