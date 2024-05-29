package vn.hoidanit.laptopshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hoidanit.laptopshop.domain.ReceiverInfo;
import vn.hoidanit.laptopshop.repository.ReceiverInfoRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ReceiverInfoService {

    @Autowired
    private ReceiverInfoRepository receiverInfoRepository;

    public ReceiverInfo saveReceiverInfo(ReceiverInfo receiverInfo) {
        return receiverInfoRepository.save(receiverInfo);
    }

    public List<ReceiverInfo> getReceiverInfosByUserId(Long userId) {
        return receiverInfoRepository.findByUserId(userId);
    }

    public ReceiverInfo getReceiverInfoById(Long id) {
        Optional<ReceiverInfo> receiverInfo = receiverInfoRepository.findById(id);
        return receiverInfo.orElse(null);
    }
}