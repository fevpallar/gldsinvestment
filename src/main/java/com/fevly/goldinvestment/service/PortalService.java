package com.fevly.goldinvestment.service;

import org.springframework.stereotype.Service;

@Service
public class PortalService {
  /*  @Autowired
    private LamaranRepository lamaranRepository;
    @Autowired
    private PelamarRepository pelamarRepository;
    @Autowired
    private PerusahaanRepository perusahaanRepository;
    @Autowired
    private LowonganRepository lowonganRepository;

    public Pelamar addPelamar(Pelamar pelamar) {
        return pelamarRepository.save(pelamar);
    }

    public Perusahaan addPerusahaan(Perusahaan perusahaan) {
        return perusahaanRepository.save(perusahaan);
    }

    public Lowongan addLowongan(Lowongan lowongan) {
        return lowonganRepository.save(lowongan);

    }

    public Lamaran addLamaran(Lamaran lamaran) {
        return lamaranRepository.save(lamaran);

    }

    public Lamaran updateLamaran(Lamaran lamaran) {
        Lamaran currentLamaran = lamaranRepository.findByCodeLamaran(lamaran.getKode());

        currentLamaran.setKode_lowongan(lamaran.getKode_lowongan());
        currentLamaran.setKode_perusahaan(lamaran.getKode_perusahaan());
        currentLamaran.setSudah_wawancara(lamaran.isSudah_wawancara());
        currentLamaran.setTanggal_wawancara(currentLamaran.getTanggal_wawancara());
        currentLamaran.setJam_wawancara(currentLamaran.getJam_wawancara());

        return lamaranRepository.save(currentLamaran);
    }

    public Pelamar updatePelamar(Pelamar pelamar) {
        Pelamar currentPelamar = pelamarRepository.findByCodePelamar(pelamar.getNoktp());

        currentPelamar.setAlamat(pelamar.getAlamat());
        currentPelamar.setNama(pelamar.getNama());
        currentPelamar.setUsia(pelamar.getUsia());

        return pelamarRepository.save(currentPelamar);
    }

    public Perusahaan updatePerusahaan(Perusahaan perusahaan) {
        Perusahaan currentPerusahaan = perusahaanRepository.findByCodePerusahaan(perusahaan.getKode());
        currentPerusahaan.setAlamat(perusahaan.getAlamat());
        currentPerusahaan.setNama(perusahaan.getNama());
        currentPerusahaan.setIndustri(perusahaan.getIndustri());
        currentPerusahaan.setIndustri(perusahaan.getIndustri());
        return perusahaanRepository
                .save(currentPerusahaan);
    }

    public Lowongan updateLowongan(Lowongan lowongan) {
        Lowongan currentLowongan = lowonganRepository.findByCodeLowongan(lowongan.getKode());
        currentLowongan.setDeskripsi(lowongan.getDeskripsi());
        currentLowongan.setIndustri(lowongan.getIndustri());
        currentLowongan.setKualifikasi(lowongan.getKualifikasi());
        currentLowongan.setPosisi(lowongan.getPosisi());
        return lowonganRepository.save(currentLowongan);
    }

    public List<Lamaran> getAllLamaran() {
        return lamaranRepository.getAllLamaran();
    }

    public List<Lowongan> getAllLowongan() {
        return lowonganRepository.getAllLowongan();
    }

    public List<Perusahaan> getAllPerusahaan()   {
        return perusahaanRepository.getAllPerusahaan();
    }

    public List<Pelamar> getAllPelamar() {
        return pelamarRepository.getAllPelamar();
    }

    public Lamaran findByKode(String kode) {
        return lamaranRepository.findByCodeLamaran(kode);
    }

    public List<Object[]> getAllNoShowInInterview() {
        return lamaranRepository.findAllNoShowInterview();
    }
*/
}
