package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.ShareDto;
import com.masterpiece.stockmarketsimulator.entities.Share;
import com.masterpiece.stockmarketsimulator.repositories.ShareRepository;
import org.springframework.stereotype.Service;


@Service
public class ShareServiceImpl implements ShareService {

    private final ShareRepository shareRepository;

    public ShareServiceImpl(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }

    @Override
    public void create(ShareDto dto) {
        Share share = new Share();
        populateAndSave(dto, share);
    }


    private void populateAndSave(ShareDto dto, Share share){
        share.setCompanyName(dto.getCompanyName());
        share.setDate(dto.getDate());
        share.setQuantity(dto.getQuantity());
        share.setUnityPrice(dto.getUnityPrice());
        shareRepository.save(share);

    }
}
