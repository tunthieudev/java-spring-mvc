package vn.hoidanit.laptopshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hoidanit.laptopshop.domain.ReceiverInfo;
import vn.hoidanit.laptopshop.repository.ReceiverInfoRepository;
import java.util.List;

@Service
public class ReceiverInfoService {

    @Autowired
    private ReceiverInfoRepository receiverInfoRepository;

    public List<ReceiverInfo> getReceiverInfosByUserId(Long userId) {
        return receiverInfoRepository.findByUserId(userId);
    }
}