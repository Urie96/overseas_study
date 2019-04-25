package me.urie.overseas_study.cotroller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.urie.overseas_study.bean.Msg;
import me.urie.overseas_study.bean.School;
import me.urie.overseas_study.mapper.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {
    @Autowired
    SchoolMapper schoolMapper;

    @RequestMapping("/conditions")
    public Msg setConditons() {
        return Msg.success().add("countries", schoolMapper.selectCountries());
    }

    @RequestMapping("/hotSchools")
    public Msg setHotSchools() {
        return Msg.success().add("hotSchools", schoolMapper.getSixSchools());
    }

    @RequestMapping("/school_detail")
    public Msg sendSchoolDetail(String sname) {
        schoolMapper.addHit(sname);
        return Msg.success().add("school", schoolMapper.selectSchoolByName(sname));
    }

    @RequestMapping("/getOnePageSchools.json")
    public Msg getOnePageSchools(School preSchool, int pageNo) {
        if ("全部".equals(preSchool.getScountry())) {
            preSchool.setScountry(null);
        }
        if ("全部".equals(preSchool.getSnature())) {
            preSchool.setSnature(null);
        }
        PageHelper.startPage(pageNo, 5);
        List<School> schools = schoolMapper.getSchoolsByCondition(preSchool);
        PageInfo<School> pageInfo = new PageInfo<>(schools);
        return Msg.success().add("schools", schools).add("pageInfo", pageInfo);
    }
}
