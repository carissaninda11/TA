import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Pasien {
    private String nama;
    private int id;

    public Pasien(String nama, int id) {
        this.nama = nama;
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public int getId() {
        return id;
    }
}

class Registrasi {
    private List<String> logRegistrasi = new ArrayList<>();

    public void registrasiRawatJalan(Pasien pasien) {
        String log = "Registrasi Rawat Jalan untuk " + pasien.getNama() + " (ID: " + pasien.getId() + ")";
        System.out.println(log);
        logRegistrasi.add(log);
        // Logika registrasi Rawat Jalan
    }

    public void registrasiKonsultasiDokter(Pasien pasien, String jenisDokter) {
        String log = "Registrasi Konsultasi Dokter untuk " + pasien.getNama() + " (ID: " + pasien.getId() + ")";
        log += "\nJenis Dokter: " + jenisDokter;
        System.out.println(log);
        logRegistrasi.add(log);
        // Logika registrasi Konsultasi Dokter
    }

    public void registrasiRawatInap(Pasien pasien, String tipeKelas) {
        String log = "Registrasi Rawat Inap untuk " + pasien.getNama() + " (ID: " + pasien.getId() + ")";
        log += "\nTipe Kelas: " + tipeKelas;
        System.out.println(log);
        logRegistrasi.add(log);
        // Logika registrasi Rawat Inap
    }

    public void registrasiFarmasi(Pasien pasien) {
        String log = "Registrasi Farmasi untuk " + pasien.getNama() + " (ID: " + pasien.getId() + ")";
        System.out.println(log);
        logRegistrasi.add(log);
        // Logika registrasi Farmasi
    }

    public List<String> getLogRegistrasi() {
        return logRegistrasi;
    }
}

public class AplikasiRegistrasiRumahSakit {
    private static final String FILE_PATH = "registrasi_log.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan Nama Pasien: ");
        String namaPasien = scanner.nextLine();
        System.out.print("Masukkan ID Pasien: ");
        int idPasien = scanner.nextInt();

        Pasien pasien = new Pasien(namaPasien, idPasien);
        Registrasi registrasi = new Registrasi();

        System.out.println("\nPilih Layanan:");
        System.out.println("1. Rawat Jalan");
        System.out.println("2. Konsultasi Dokter");
        System.out.println("3. Rawat Inap");
        System.out.println("4. Farmasi");

        System.out.print("Masukkan pilihan layanan (1-4): ");
        int pilihanLayanan = scanner.nextInt();

        switch (pilihanLayanan) {
            case 1:
                registrasi.registrasiRawatJalan(pasien);
                break;
            case 2:
                System.out.println("\nPilih Jenis Dokter:");
                System.out.println("1. Umum");
                System.out.println("2. Spesialis");
                System.out.print("Masukkan pilihan jenis dokter (1-2): ");
                int pilihanDokter = scanner.nextInt();
                if (pilihanDokter == 1) {
                    registrasi.registrasiKonsultasiDokter(pasien, "Umum");
                } else if (pilihanDokter == 2) {
                    System.out.println("\nPilih Spesialisasi:");
                    System.out.println("1. Penyakit Dalam");
                    System.out.println("2. Anak");
                    System.out.println("3. Saraf");
                    System.out.println("4. Kandungan");
                    System.out.println("5. Kulit");
                    System.out.println("6. Pernafasan");
                    System.out.print("Masukkan pilihan spesialisasi (1-6): ");
                    int pilihanSpesialisasi = scanner.nextInt();
                    String spesialisasi = getSpesialisasi(pilihanSpesialisasi);
                    registrasi.registrasiKonsultasiDokter(pasien, spesialisasi);
                }
                break;
            case 3:
                System.out.println("\nPilih Tipe Kelas Rawat Inap:");
                System.out.println("1. Kelas 1");
                System.out.println("2. Kelas 2");
                System.out.println("3. VIP");
                System.out.print("Masukkan pilihan tipe kelas (1-3): ");
                int pilihanKelas = scanner.nextInt();
                String tipeKelas = getTipeKelas(pilihanKelas);
                registrasi.registrasiRawatInap(pasien, tipeKelas);
                break;
            case 4:
                registrasi.registrasiFarmasi(pasien);
                break;
            default:
                System.out.println("Pilihan layanan tidak valid.");
        }

        System.out.println("\nTerima kasih telah melakukan registrasi!");

        // Simpan data registrasi ke dalam file
        simpanLogRegistrasi(registrasi.getLogRegistrasi());
    }

    private static String getSpesialisasi(int pilihan) {
        switch (pilihan) {
            case 1:
                return "Penyakit Dalam";
            case 2:
                return "Anak";
            case 3:
                return "Saraf";
            case 4:
                return "Kandungan";
            case 5:
                return "Kulit";
            case 6:
                return "Pernafasan";
            default:
                return "Umum";
        }
    }

    private static String getTipeKelas(int pilihan) {
        switch (pilihan) {
            case 1:
                return "Kelas 1";
            case 2:
                return "Kelas 2";
            case 3:
                return "VIP";
            default:
                return "Kelas 1";
        }
    }

    private static void simpanLogRegistrasi(List<String> logRegistrasi) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            for (String log : logRegistrasi) {
                writer.println(log);
            }
            System.out.println("Data registrasi disimpan ke dalam file: " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Gagal menyimpan data registrasi ke dalam file.");
            e.printStackTrace();
        }
    }
}
