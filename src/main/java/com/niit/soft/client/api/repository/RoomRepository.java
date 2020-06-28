package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Tao
 */
public interface RoomRepository extends JpaRepository<Room, Long> {


    @Query(value = "select room_leader_job_number from room as r where r.id=?1", nativeQuery = true)
    String findLeaderNumberById(Long id);

    /**
     * 根据房间id查询房间名
     *
     * @param id
     * @return
     */
    @Query(value = "select r.name from first_smart_campus.room r where r.id = ?1", nativeQuery = true)
    String findNameById(Long id);


    /**
     * 根据房间查询出楼栋名
     *
     * @param roomId
     * @return
     */
    @Query(value = "SELECT T.name " +
            "FROM first_smart_campus.room R,first_smart_campus.tower T " +
            "WHERE R.tower_id = T.pk_tower_id " +
            "AND R.id = ?1", nativeQuery = true)
    String findTowerNameById(Long roomId);

    /**
     * 根据房间Id来获取寝室长号码
     *
     * @param id
     * @return
     */
    @Query(value = "select r.room_leader_job_number from first_smart_campus.room r where r.id = ?1", nativeQuery = true)
    String findRoomLearderNumberById(Long id);

}
