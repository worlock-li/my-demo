package com.stady.blog.service.impl;

import com.stady.blog.dao.TagRepository;
import com.stady.blog.exception.NotFoundException;
import com.stady.blog.pojo.Blog;
import com.stady.blog.pojo.Tag;
import com.stady.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Page<Tag> listTags(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTags() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTags(String ids) {
        return tagRepository.findAllById(convertToList(ids));
    }

    private List<Long> convertToList(String ids) {
        ArrayList<Long> list = new ArrayList<>();
        if ((!"".equals(ids)) && ids != null) {
            String[] idArray = ids.split(",");
            for (String s : idArray) {
                list.add(Long.parseLong(s));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }

    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag tg = tagRepository.getOne(id);
        if (tg == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(tag, tg);
        return tagRepository.save(tg);
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Tag getByName(String name) {
        return tagRepository.findTagByName(name);
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return tagRepository.findTop(pageable);
    }

}
