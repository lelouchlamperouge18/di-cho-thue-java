package com.fit.hcmus.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fit.hcmus.model.TaiKhoan;
import com.fit.hcmus.repository.TaiKhoanRepository;

@RestController
@RequestMapping("/api")
public class TaiKhoanController {
	@Autowired
	private TaiKhoanRepository taiKhoanRepository;
	
	@GetMapping("/taiKhoans")
	//Lấy tất cả tài khoản hoặc theo username
	public ResponseEntity<List<TaiKhoan>> getAllTaiKhoan(@RequestParam(required = false) String username){
		try {
			List<TaiKhoan> taiKhoans = new ArrayList<TaiKhoan>();
			
			if(username == null) {
				taiKhoanRepository.findAll().forEach(taiKhoans::add);
			}else {
				taiKhoanRepository.findByUsername(username).forEach(taiKhoans::add);
			}
			
			if(taiKhoans.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(taiKhoans, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Lấy tài khoản theo id
	@GetMapping("/taiKhoans/{id}")
	public ResponseEntity<TaiKhoan> getTaiKhoanById(@PathVariable("id") int id){
		Optional<TaiKhoan> taiKhoan = taiKhoanRepository.findById(id);
		
		if(taiKhoan.isPresent()) {
			return new ResponseEntity<>(taiKhoan.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Tạo tài khoản
	@PostMapping("/taiKhoans")
	public ResponseEntity<TaiKhoan> createTaiKhoan(@RequestBody TaiKhoan taiKhoan){
		try {
			System.out.println(taiKhoan);
			TaiKhoan _taiKhoan = taiKhoanRepository.save(new TaiKhoan(taiKhoan.getUsername(), 
					taiKhoan.getPassword(), taiKhoan.getTrangThai(), taiKhoan.getVaiTro()));
			return new ResponseEntity<>(_taiKhoan, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Cập nhật tài khoản (VD là mật khẩu). Nếu muốn cập nhập theo logic thì tùy chỉnh
	@PutMapping("/taiKhoans/{id}")
	public ResponseEntity<TaiKhoan> updateTaiKhoan(@PathVariable("id") int id, @RequestBody TaiKhoan taiKhoan){
		Optional<TaiKhoan> taiKhoanData = taiKhoanRepository.findById(id);
		if(taiKhoanData.isPresent()) {
			TaiKhoan _taiKhoan = taiKhoanData.get();
			_taiKhoan.setPassword(taiKhoan.getPassword());
			return new ResponseEntity<>(taiKhoanRepository.save(_taiKhoan), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Xóa tài khoản
	@DeleteMapping("/taiKhoans/{id}")
	public ResponseEntity<TaiKhoan> deleteTaiKhoan(@PathVariable("id") int id){
		try {
			taiKhoanRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
