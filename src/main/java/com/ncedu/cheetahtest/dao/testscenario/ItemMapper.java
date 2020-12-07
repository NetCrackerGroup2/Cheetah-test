package com.ncedu.cheetahtest.dao.testscenario;

import com.ncedu.cheetahtest.entity.testscenario.ItemDTO;
import com.ncedu.cheetahtest.entity.testscenario.ItemKind;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper implements RowMapper<ItemDTO> {
    public static final String ID = "id_item";
    public static final String TITLE = "title";
    public static final String PRIORITY = "priority";
    public static final String DESCRIPTION = "description";
    public static final String KIND = "kind";
    @Override
    public ItemDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setId(resultSet.getInt(ID));
        itemDTO.setTitle(resultSet.getString(TITLE));
        itemDTO.setPriority(resultSet.getInt(PRIORITY));
        itemDTO.setDescription(resultSet.getString(DESCRIPTION));
        itemDTO.setKind(ItemKind.valueOf(resultSet.getString(KIND)));

        return itemDTO;
    }
}
